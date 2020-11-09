package com.yosep.msa.yoscouponapi.coupon.repository

import com.yosep.msa.yoscouponapi.coupon.domain.CouponForUser
import com.yosep.msa.yoscouponapi.coupon.domain.CouponState
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.DisplayNameGeneration
import org.junit.jupiter.api.DisplayNameGenerator
import org.junit.jupiter.api.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.annotation.Rollback
import org.springframework.test.context.junit4.SpringRunner
import java.time.LocalDateTime
import javax.transaction.Transactional

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores::class)
@RunWith(SpringRunner::class)
@SpringBootTest
class CouponRepositoryTest(@Autowired
                           var couponRepository: CouponRepository) {

    @Test
    @Rollback(value = true)
    @DisplayName(value="coupon 생성 repository 테스트")
    fun createCouponTest() {
        var coupon = CouponForUser(
                "coupon-create-test",
                "coupon-create-test",
                CouponState.START,
                1,
                LocalDateTime.now(),
                LocalDateTime.now())

        var createdCoupon = couponRepository.save(coupon)
        println(createdCoupon.toString())
    }
}