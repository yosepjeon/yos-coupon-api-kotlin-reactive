package com.yosep.msa.yoscouponapi.coupon.service

import com.yosep.msa.yoscouponapi.coupon.domain.CouponState
import com.yosep.msa.yoscouponapi.coupon.domain.withAmount.CouponWithAmount
import com.yosep.msa.yoscouponapi.coupon.domain.withAmount.CouponWithAmountToCreateDto
import com.yosep.msa.yoscouponapi.coupon.domain.withAmount.CouponWithAmountToUpdateDto
import com.yosep.msa.yoscouponapi.coupon.repository.CouponRepository
import com.yosep.msa.yoscouponapi.coupon.repository.CouponWithAmountRepository
import org.junit.Assert
import org.junit.Before
import org.junit.jupiter.api.*
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.annotation.Rollback
import org.springframework.test.context.junit4.SpringRunner
import java.time.LocalDateTime
import java.util.*
import java.util.Optional.empty

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores::class)
@Rollback(value = true)
@SpringBootTest
class CouponWithAmountServiceTest(
        @Autowired
        var couponWithAmountService: CouponWithAmountService,

        @Autowired
        var couponRepository: CouponRepository,

        @Autowired
        var couponWithAmountRepository: CouponWithAmountRepository
) {
    @BeforeEach
    @Rollback(value = true)
    fun saveCouponBeforeTest() {
        var i = 0;

        while (i < 10) {
            i++
            var coupon = CouponWithAmount(
                    "coupon-create-test$i",
                    "coupon-create-test$i",
                    CouponState.START,
                    i,
                    LocalDateTime.now(),
                    LocalDateTime.now(),
                    LocalDateTime.now(),
                    LocalDateTime.now())

            couponRepository.save(coupon)
        }
    }

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

        // Given
        var coupon = CouponWithAmountToCreateDto(
                "coupon-create-test",
                1,
                "2016-10-31 23:59:59",
                "2016-10-31 23:59:59")

        var createdCoupon = couponWithAmountService.create(coupon)

        var couponWithAmountToUpdateDto = CouponWithAmountToUpdateDto(
                createdCoupon.couponId,
                "coupon-create-test",
                10,
                CouponState.START,
                "2017-10-31 23:59:59",
                "2019-10-31 23:59:59")

        // When
        var updatedCoupon = couponWithAmountService.update(couponWithAmountToUpdateDto)

        // Then
        println("created coupon= $createdCoupon")
        println("updated coupon= $updatedCoupon")

        Assertions.assertNotEquals(createdCoupon.total,updatedCoupon.total)
    }

    @Test
    @DisplayName("Coupon 삭제 Service 테스트")
    fun deleteCouponServiceTest() {
        var coupons = couponWithAmountRepository.findAll()

        println("삭제전")
        for(coupon in coupons) {
            println("$coupon!")
        }

        // Given
        var couponIdForDelete = coupons[0].couponId

        // When & Then
        couponWithAmountService.deleteById(couponIdForDelete)
        Assertions.assertEquals(true,couponWithAmountRepository.findById(couponIdForDelete).isEmpty)
    }
}