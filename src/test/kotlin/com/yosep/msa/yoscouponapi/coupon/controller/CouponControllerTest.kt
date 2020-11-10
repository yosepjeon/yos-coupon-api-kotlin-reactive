package com.yosep.msa.yoscouponapi.coupon.controller

import com.netflix.discovery.converters.Auto
import com.yosep.msa.yoscouponapi.common.BaseControllerTest
import com.yosep.msa.yoscouponapi.coupon.domain.Coupon
import com.yosep.msa.yoscouponapi.coupon.domain.CouponDTO
import com.yosep.msa.yoscouponapi.coupon.domain.CouponForUser
import com.yosep.msa.yoscouponapi.coupon.service.CouponService
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.*

import org.junit.jupiter.api.Assertions.*
import org.modelmapper.ModelMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext
import org.springframework.security.oauth2.client.OAuth2RestTemplate
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails
import org.springframework.security.oauth2.common.OAuth2AccessToken
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf
import org.springframework.test.annotation.Rollback
import org.springframework.test.context.event.annotation.BeforeTestClass
import org.springframework.test.context.event.annotation.BeforeTestMethod
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.web.client.RestTemplate
import java.time.LocalDateTime

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores::class)
internal class CouponControllerTest: BaseControllerTest(

) {
    @Autowired
    lateinit var couponService: CouponService
    @Autowired
    lateinit var restTemplate: RestTemplate
    @Auto
    lateinit var resourceForTest: ResourceOwnerPasswordResourceDetails

    lateinit var accessTokenForTest: OAuth2AccessToken
    lateinit var clientContextForTest: DefaultOAuth2ClientContext
    lateinit var oAuth2RestTemplateForTest: OAuth2RestTemplate

    @Before
    fun setUp() {
        resourceForTest = ResourceOwnerPasswordResourceDetails()
        resourceForTest.username = "test"
        resourceForTest.password = "123123123"
        resourceForTest.accessTokenUri = "http://3.35.107.191:8095/oauth/token"
        resourceForTest.clientId = "yoggaebi"
        resourceForTest.clientSecret = "pass"
        resourceForTest.grantType = "password"

        clientContextForTest = DefaultOAuth2ClientContext()
        oAuth2RestTemplateForTest = OAuth2RestTemplate(resourceForTest, clientContextForTest)
        accessTokenForTest = oAuth2RestTemplateForTest.accessToken

    }

    @DisplayName(value = "model mapper 테스트")
    @Test
    fun modelMapperTest() {
        val couponDTO = CouponDTO("coupon-test1", 1)
        val coupon:Coupon = modelMapper.map(couponDTO, CouponForUser::class.java)

        println(couponDTO.toString())
        println(coupon.toString())
        println("resourceForTest= $resourceForTest")
    }

    @Test
    @Rollback(value = true)
    @DisplayName(value = "쿠폰 생성 테스트")
    @Throws(Exception::class)
    fun createCouponTest() {
        println("---------------------- create test ------------------------")
        val couponDTO = CouponDTO("쿠폰-생성-테스트1",1)
        mockMvc.perform(MockMvcRequestBuilders.post("/api/coupons").with(csrf())
                .header(HttpHeaders.AUTHORIZATION)
        )

    }
}