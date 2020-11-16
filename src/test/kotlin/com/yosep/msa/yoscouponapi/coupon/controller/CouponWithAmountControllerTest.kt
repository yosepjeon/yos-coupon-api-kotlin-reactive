package com.yosep.msa.yoscouponapi.coupon.controller

import com.netflix.discovery.converters.Auto
import com.yosep.msa.yoscouponapi.common.BaseControllerTest
import com.yosep.msa.yoscouponapi.coupon.domain.*
import com.yosep.msa.yoscouponapi.coupon.domain.withAmount.CouponWithAmount
import com.yosep.msa.yoscouponapi.coupon.domain.withAmount.CouponWithAmountToCreateDto
import com.yosep.msa.yoscouponapi.coupon.domain.withAmount.CouponWithAmountToUpdateDto
import com.yosep.msa.yoscouponapi.coupon.repository.CouponWithAmountRepository
import com.yosep.msa.yoscouponapi.coupon.service.CouponWithAmountService
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.DisplayNameGeneration
import org.junit.jupiter.api.DisplayNameGenerator
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.hateoas.MediaTypes
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.restdocs.headers.HeaderDocumentation.*
import org.springframework.restdocs.hypermedia.HypermediaDocumentation.linkWithRel
import org.springframework.restdocs.hypermedia.HypermediaDocumentation.links
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document
import org.springframework.restdocs.payload.JsonFieldType
import org.springframework.restdocs.payload.PayloadDocumentation.*
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext
import org.springframework.security.oauth2.client.OAuth2RestTemplate
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails
import org.springframework.security.oauth2.common.OAuth2AccessToken
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf
import org.springframework.test.annotation.Rollback
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.web.client.RestTemplate

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores::class)
internal class CouponWithAmountControllerTest : BaseControllerTest(

) {
    @Autowired
    lateinit var couponWithAmountService: CouponWithAmountService

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
        val couponDTO = CouponWithAmountToCreateDto("coupon-test1", 1, "2019-09-03 00:00:00", "2019-09-03 00:00:00")
        val coupon: Coupon = modelMapper.map(couponDTO, CouponWithAmount::class.java)

        println(couponDTO.toString())
        println(coupon.toString())
        println("resourceForTest= $resourceForTest")
    }

    @Test
    @Rollback(value = true)
    @DisplayName(value = "특정 쿠폰 조회 테스트")
    @Throws(Exception::class)
    fun findCouponWithAmountTest() {

    }

    @Test
    @Rollback(value = true)
    @DisplayName(value = "쿠폰 생성 테스트")
    @Throws(Exception::class)
    fun createCouponWithAmounToCreateControllerTest() {
        println("---------------------- create test ------------------------")
        val couponDTO = CouponWithAmountToCreateDto("쿠폰-생성-테스트1", 1, "2019-09-03 00:00:00", "2019-09-03 00:00:00")
        mockMvc.perform(MockMvcRequestBuilders.post("/api/coupons").with(csrf())
                .header(HttpHeaders.AUTHORIZATION, getBearerToken(accessTokenForTest))
                .contentType(MediaType.APPLICATION_JSON).accept(MediaTypes.HAL_JSON_VALUE)
                .content(objectMapper.writeValueAsString(couponDTO)))
                .andDo(print()).andExpect(status().isCreated)
                .andDo(
                        document("create-coupon",
                                links(
                                        linkWithRel("self").description("자기 자신을 가리키는 링크로 get-coupon과 일맥상통합니다."),
                                        linkWithRel("get-coupons").description("쿠폰 리스트를 가져오는 링크"),
                                        linkWithRel("get-coupon").description("특정 쿠폰을 가져오는 링크"),
                                        linkWithRel("patch-coupon").description("특정 쿠폰의 일부 프로퍼티를 수정하는 링크"),
                                        linkWithRel("put-coupon").description("툭정 쿠폰의 전체 프로퍼티를 수정하는 링크"),
                                        linkWithRel("profile").description("profile 링크")
                                ),
                                requestHeaders(
                                        headerWithName(HttpHeaders.CONTENT_TYPE).description("application/json;charset=UTF-8"),
                                        headerWithName(HttpHeaders.AUTHORIZATION).description("Bearer token-value MSA 모든 서비스를 이용하기 위해서는 해당 인증값을 반드시 넣어야 합니다."),
                                        headerWithName(HttpHeaders.ACCEPT).description("accept header")
                                ),
                                requestFields(
                                        fieldWithPath("couponName").type(JsonFieldType.STRING).description("쿠폰 이름"),
                                        fieldWithPath("total").type(JsonFieldType.NUMBER).description("쿠폰 재고"),
                                        fieldWithPath("startDate").type(JsonFieldType.STRING).description("쿠폰 시작시간"),
                                        fieldWithPath("endDate").type(JsonFieldType.STRING).description("쿠폰 종료시간")
                                ),
                                responseHeaders(
                                        headerWithName(HttpHeaders.LOCATION).description("Location Header"),
                                        headerWithName(HttpHeaders.CONTENT_TYPE).description("Content type")
                                ),
                                responseFields(
                                        fieldWithPath("coupon.couponId").type(JsonFieldType.STRING).description("쿠폰 아이디"),
                                        fieldWithPath("coupon.couponName").type(JsonFieldType.STRING).description("쿠폰 이름"),
                                        fieldWithPath("coupon.couponState").type(JsonFieldType.STRING).description("쿠폰 상태"),
                                        fieldWithPath("coupon.total").type(JsonFieldType.NUMBER).description("쿠폰 재고"),
                                        fieldWithPath("coupon.startDate").type(JsonFieldType.OBJECT).description("쿠폰 시작일"),
                                        fieldWithPath("coupon.endDate").type(JsonFieldType.OBJECT).description("쿠폰 종료시간"),
                                        fieldWithPath("coupon.createdDate").type(JsonFieldType.OBJECT).description("쿠폰 생성일"),
                                        fieldWithPath("coupon.lastModifiedDate").type(JsonFieldType.OBJECT).description("쿠폰 수정일"),
                                        fieldWithPath("_links.self.href").description("자기자신을 가리키는 링크입니다."),
                                        fieldWithPath("_links.get-coupons.href").description("특정 쿠폰 하나를 가져오는 링크입니다."),
                                        fieldWithPath("_links.get-coupon.href").description("쿠폰 리스트를 가져오는 링크입니다."),
                                        fieldWithPath("_links.patch-coupon.href").description("특정 쿠폰의 일부 프로퍼티를 수정하는 링크"),
                                        fieldWithPath("_links.put-coupon.href").description("특정 쿠폰의 전체 프로퍼티를 수정하는 링크"),
                                        fieldWithPath("_links.profile.href").description("REST DOC의 profile Link")
                                )
                                        .and(subsectionWithPath("coupon.startDate").type(JsonFieldType.OBJECT).description("쿠폰 시작 시간"))
                                        .and(subsectionWithPath("coupon.endDate").type(JsonFieldType.OBJECT).description("쿠폰 종료 시간"))
                                        .and(subsectionWithPath("coupon.createdDate").type(JsonFieldType.OBJECT).description("쿠폰 생성 시간"))
                                        .and(subsectionWithPath("coupon.lastModifiedDate").type(JsonFieldType.OBJECT).description("쿠폰 수정 시간"))

                        )
                )

    }

    @Test
    @Rollback(value = true)
    @DisplayName(value = "쿠폰 수정 테스트")
    @Throws(Exception::class)
    fun patchCouponWithAmountToUpdateControllerTest() {
        /*
        * 쿠폰 수량을 1개에서 10개로 수정 테스트
         */
        var coupon = CouponWithAmountToCreateDto(
                "coupon-create-test",
                1,
                "2016-10-31 23:59:59",
                "2016-10-31 23:59:59")

        var createdCoupon = couponWithAmountService.create(coupon)

        // Given
        val couponWithAmountToUpdateDto = CouponWithAmountToUpdateDto(
                createdCoupon.couponId,
                "coupon-create-test",
                10,
                CouponState.START,
                "2017-10-31 23:59:59",
                "2019-10-31 23:59:59")

        // When & Then
        mockMvc.perform(MockMvcRequestBuilders.patch("/api/coupons").with(csrf())
                .header(HttpHeaders.AUTHORIZATION, getBearerToken(accessTokenForTest))
                .contentType(MediaType.APPLICATION_JSON).accept(MediaTypes.HAL_JSON_VALUE)
                .content(objectMapper.writeValueAsString(couponWithAmountToUpdateDto)))
                .andDo(print()).andExpect(status().isOk)
                .andDo(
                        document("patch-coupon",
                                links(
                                        linkWithRel("self").description("자기 자신을 가리키는 링크로 patch-coupon과 일맥상통합니다."),
                                        linkWithRel("get-coupons").description("쿠폰 리스트를 가져오는 링크"),
                                        linkWithRel("get-coupon").description("특정 쿠폰을 가져오는 링크"),
                                        linkWithRel("patch-coupon").description("특정 쿠폰의 일부 프로퍼티를 수정하는 링크"),
                                        linkWithRel("put-coupon").description("툭정 쿠폰의 전체 프로퍼티를 수정하는 링크"),
                                        linkWithRel("profile").description("profile 링크")
                                ),
                                requestHeaders(
                                        headerWithName(HttpHeaders.CONTENT_TYPE).description("application/json;charset=UTF-8"),
                                        headerWithName(HttpHeaders.AUTHORIZATION).description("Bearer token-value MSA 모든 서비스를 이용하기 위해서는 해당 인증값을 반드시 넣어야 합니다."),
                                        headerWithName(HttpHeaders.ACCEPT).description("accept header")

                                ),
                                requestFields(
                                        fieldWithPath("couponId").type(JsonFieldType.STRING).description("쿠폰 아이디"),
                                        fieldWithPath("couponName").type(JsonFieldType.STRING).description("쿠폰 이름"),
                                        fieldWithPath("total").type(JsonFieldType.NUMBER).description("쿠폰 재고"),
                                        fieldWithPath("couponState").type(JsonFieldType.STRING).description("쿠폰 상태"),
                                        fieldWithPath("startDate").type(JsonFieldType.STRING).description("쿠폰 시작일"),
                                        fieldWithPath("endDate").type(JsonFieldType.STRING).description("쿠폰 종료시간")

                                ),
                                responseFields(
                                        fieldWithPath("coupon.couponId").type(JsonFieldType.STRING).description("쿠폰 아이디"),
                                        fieldWithPath("coupon.couponName").type(JsonFieldType.STRING).description("쿠폰 이름"),
                                        fieldWithPath("coupon.couponState").type(JsonFieldType.STRING).description("쿠폰 상태"),
                                        fieldWithPath("coupon.total").type(JsonFieldType.NUMBER).description("쿠폰 재고"),
                                        fieldWithPath("coupon.startDate").type(JsonFieldType.OBJECT).description("쿠폰 시작일"),
                                        fieldWithPath("coupon.endDate").type(JsonFieldType.OBJECT).description("쿠폰 종료시간"),
                                        fieldWithPath("coupon.createdDate").type(JsonFieldType.OBJECT).description("쿠폰 생성일"),
                                        fieldWithPath("coupon.lastModifiedDate").type(JsonFieldType.OBJECT).description("쿠폰 수정일"),
                                        fieldWithPath("_links.self.href").description("자기자신을 가리키는 링크입니다."),
                                        fieldWithPath("_links.get-coupons.href").description("특정 쿠폰 하나를 가져오는 링크입니다."),
                                        fieldWithPath("_links.get-coupon.href").description("쿠폰 리스트를 가져오는 링크입니다."),
                                        fieldWithPath("_links.patch-coupon.href").description("특정 쿠폰의 일부 프로퍼티를 수정하는 링크"),
                                        fieldWithPath("_links.put-coupon.href").description("특정 쿠폰의 전체 프로퍼티를 수정하는 링크"),
                                        fieldWithPath("_links.profile.href").description("REST DOC의 profile Link")
                                )
                                        .and(subsectionWithPath("coupon.startDate").type(JsonFieldType.OBJECT).description("쿠폰 시작 시간"))
                                        .and(subsectionWithPath("coupon.endDate").type(JsonFieldType.OBJECT).description("쿠폰 종료 시간"))
                                        .and(subsectionWithPath("coupon.createdDate").type(JsonFieldType.OBJECT).description("쿠폰 생성 시간"))
                                        .and(subsectionWithPath("coupon.lastModifiedDate").type(JsonFieldType.OBJECT).description("쿠폰 수정 시간"))
                        )
                )
    }

//    @Test
//    @Rollback(value = true)
//    @DisplayName(value = "쿠폰 아이디 기반 삭제 테스트")
//    @Throws(Exception::class)
//    fun deleteCouponByIdTest() {
//        // Given
//        var couponId =
//    }


    fun getBearerToken(token: OAuth2AccessToken): String? {
        println("Bearer ${token.value}")
        return "Bearer " + token.value
    }
}