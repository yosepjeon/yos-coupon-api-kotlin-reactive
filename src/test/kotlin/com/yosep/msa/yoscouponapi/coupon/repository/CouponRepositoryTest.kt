package com.yosep.msa.yoscouponapi.coupon.repository

import com.yosep.msa.yoscouponapi.coupon.domain.withAmount.CouponWithAmount
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
import java.time.format.DateTimeFormatter

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores::class)
//@RunWith(SpringRunner::class)
@Rollback(value = true)
@SpringBootTest
class CouponRepositoryTest(
        @Autowired
        var couponRepository: CouponRepository,

        @Autowired
        var couponWithAmountRepository: CouponWithAmountRepository
) {

    @Test
    @DisplayName(value = "coupon 생성 repository 테스트")
    fun createCouponTest() {
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
    }

    @Test
    @DisplayName(value = "coupon 수정 repository 테스트")
    fun updateCouponTest() {
        var coupon = CouponWithAmount(
                "coupon-create-test",
                "coupon-create-test",
                CouponState.START,
                1,
                LocalDateTime.now(),
                LocalDateTime.now(),
                LocalDateTime.now(),
                LocalDateTime.now())

        couponWithAmountRepository.save(coupon)

        var findedCoupon = couponWithAmountRepository.findById(coupon.couponId).get()
        findedCoupon.total = 10
        couponWithAmountRepository.save(findedCoupon)

        var updatedCoupon = couponWithAmountRepository.findById(findedCoupon.couponId)

        println("기존 쿠폰: $coupon")
        println("수정된 쿠폰: $updatedCoupon")

    }

    @Test
    @DisplayName(value = "문자열 LocalDateTime 변환 테스트")
    fun parseStringToLocalDateTimeTest() {
        println(LocalDateTime.parse("2016-10-31 23:59:59", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))

    }
}