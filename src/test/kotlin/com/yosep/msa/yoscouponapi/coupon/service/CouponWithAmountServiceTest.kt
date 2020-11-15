package com.yosep.msa.yoscouponapi.coupon.service

import com.yosep.msa.yoscouponapi.coupon.domain.CouponState
import com.yosep.msa.yoscouponapi.coupon.domain.withAmount.CouponWithAmount
import com.yosep.msa.yoscouponapi.coupon.domain.withAmount.CouponWithAmountToUpdateDto
import com.yosep.msa.yoscouponapi.coupon.repository.CouponRepository
import com.yosep.msa.yoscouponapi.coupon.repository.CouponWithAmountRepository
import org.junit.jupiter.api.*
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.annotation.Rollback
import org.springframework.test.context.junit4.SpringRunner
import java.time.LocalDateTime
import java.util.*

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores::class)
@Rollback(value = true)
@SpringBootTest
class CouponWithAmountServiceTest(
        @Autowired
        var couponRepository: CouponRepository,

        @Autowired
        var couponWithAmountRepository: CouponWithAmountRepository
) {

    @Test
    @DisplayName(value = "coupon 생성 Service 테스트")
    fun createCouponServiceTest() {
        // Given
        var coupon = CouponWithAmount(
                "coupon-create-test",
                "coupon-create-test",
                CouponState.START,
                1,
                LocalDateTime.now(),
                LocalDateTime.now(),
                LocalDateTime.now(),
                LocalDateTime.now())

        var createdCoupon = couponRepository.save(coupon)
        println(createdCoupon.toString())

        var couponId: String

        do {
            couponId = "coupon_" + UUID.randomUUID()
            var findedCoupon = couponRepository.findById(couponId)
        } while (!findedCoupon.isEmpty)

        var newCoupon = CouponWithAmount(
                couponId,
                "coupon-create-test",
                CouponState.START,
                1,
                LocalDateTime.now(),
                LocalDateTime.now(),
                LocalDateTime.now(),
                LocalDateTime.now()
        )

        // When
        var newCreatedCoupon = couponRepository.save(newCoupon)

        // Then
        Assertions.assertEquals(newCoupon,newCreatedCoupon)
        println(newCreatedCoupon.toString())
    }

    @Test
    @DisplayName("Coupon 수정 Service 테스트")
    fun updateCouponServiceTest() {
        var couponDTO = CouponWithAmountToUpdateDto("coupon-create-test")
        println(coupon)

        // Given
        var coupon = CouponWithAmount(
                "coupon-create-test",
                "coupon-create-test",
                CouponState.START,
                1,
                LocalDateTime.now(),
                LocalDateTime.now(),
                LocalDateTime.now(),
                LocalDateTime.now())

        var findedCoupon = couponWithAmountRepository.findById(coupon.couponId)

    }
}