//package com.yosep.msa.yoscouponapi.coupon.controller;
//
//import com.netflix.discovery.converters.Auto;
//import com.yosep.msa.yoscouponapi.common.BaseControllerTest;
//import org.junit.Before;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.DisplayNameGeneration;
//import org.junit.jupiter.api.DisplayNameGenerator;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
//import org.springframework.security.oauth2.client.OAuth2RestTemplate;
//import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;
//import org.springframework.security.oauth2.common.OAuth2AccessToken;
//import org.springframework.web.client.RestTemplate;
//
//@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
//public class CouponCtrollerTest extends BaseControllerTest {
////    @Autowired
////    ProductRepository productRepository;
////
////    @Autowired
////    ProductService productService;
//
//    @Autowired
//    RestTemplate restTemplate;
//
//    @Auto
//    ResourceOwnerPasswordResourceDetails resourceForTest;
//
//    OAuth2AccessToken accessTokenForTest;
//    DefaultOAuth2ClientContext clientContextForTest;
//    OAuth2RestTemplate oAuth2RestTemplateForTest;
//
//    @Before
//    public void setup() {
//        resourceForTest = new ResourceOwnerPasswordResourceDetails();
//        resourceForTest.setUsername("test");
//        resourceForTest.setPassword("123123123");
//        resourceForTest.setAccessTokenUri("http://3.35.107.191:" + 8095 + "/oauth/token");
//        resourceForTest.setClientId("yoggaebi");
//        resourceForTest.setClientSecret("pass");
//        resourceForTest.setGrantType("password");
//
//        clientContextForTest = new DefaultOAuth2ClientContext();
//
//        oAuth2RestTemplateForTest = new OAuth2RestTemplate(resourceForTest, clientContextForTest);
//
//        accessTokenForTest = oAuth2RestTemplateForTest.getAccessToken();
//    }
//
//    @Test
//    @DisplayName(value = "모든 쿠폰을 가져오는 테스트")
//    public void getAllCouponTest() {
////        mockMvc
//    }
//}
