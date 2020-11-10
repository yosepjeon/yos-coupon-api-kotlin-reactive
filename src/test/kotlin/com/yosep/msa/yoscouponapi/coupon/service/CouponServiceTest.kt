package com.yosep.msa.yoscouponapi.coupon.service

import com.yosep.msa.yoscouponapi.coupon.domain.CouponForUser
import com.yosep.msa.yoscouponapi.coupon.domain.CouponState
import com.yosep.msa.yoscouponapi.coupon.repository.CouponRepository
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
import java.util.*

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores::class)
@RunWith(SpringRunner::class)
@Rollback(value= true)
@SpringBootTest
class CouponServiceTest(
        @Autowired
        var couponRepository: CouponRepository
) {
    @Test
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

        var couponId:String = "coupon-create-test"
        var findedCoupon = couponRepository.findById(couponId)

        while (!findedCoupon.isEmpty) {
            println("중복")
            break
        }

        do {
            couponId = "coupon_" + UUID.randomUUID()
            var findedCoupon = couponRepository.findById(couponId)
        }while (!findedCoupon.isEmpty)

        var newCoupon = CouponForUser(
                couponId,
                "coupon-create-test",
                CouponState.START,
                1,
                LocalDateTime.now(),
                LocalDateTime.now()
        )

        var newCreatedCoupon = couponRepository.save(newCoupon)

        println(newCreatedCoupon.toString())
    }
}