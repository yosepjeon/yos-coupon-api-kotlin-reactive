package com.yosep.msa.yoscouponapi.coupon.controller

import com.yosep.msa.yoscouponapi.common.BaseControllerTest
import com.yosep.msa.yoscouponapi.coupon.domain.Coupon
import com.yosep.msa.yoscouponapi.coupon.domain.CouponDTO
import com.yosep.msa.yoscouponapi.coupon.service.CouponService
import org.junit.jupiter.api.*

import org.junit.jupiter.api.Assertions.*
import org.modelmapper.ModelMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.annotation.Rollback
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.web.client.RestTemplate

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores::class)
internal class CouponControllerTest: BaseControllerTest() {
    @Autowired
    lateinit var couponService: CouponService

    @Autowired
    lateinit var restTemplate: RestTemplate

    @BeforeEach
    fun setUp() {
    }

    @Test
    @DisplayName(value = "")
    fun modelMapperTest() {
        val couponDTO = CouponDTO("coupon-test1", 1)
        val coupon:Coupon = modelMapper.map(couponDTO, Coupon::class.java)

        println(couponDTO.toString())
        println(coupon.toString())
    }

    @Test
    @Rollback(value = true)
    @DisplayName(value = "쿠폰 생성 테스트")
    @Throws(Exception::class)
    fun createCouponTest() {
        println("---------------------- create test ------------------------")
//        val couponDTO = CouponDTO("coupon-test1","쿠폰-테스트1",1)
//        mockMvc.perform(MockMvcRequestBuilders.post("/api/coupons/"))

    }
}