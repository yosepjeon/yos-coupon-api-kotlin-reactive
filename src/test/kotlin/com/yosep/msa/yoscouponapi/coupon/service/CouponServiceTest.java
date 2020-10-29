//package com.yosep.msa.yoscouponapi.coupon.service;
//
//import com.yosep.msa.yoscouponapi.coupon.domain.Coupon;
//import com.yosep.msa.yoscouponapi.coupon.domain.CouponDTO;
//import com.yosep.msa.yoscouponapi.coupon.repository.CouponRepository;
//import com.yosep.msa.yoscouponapi.stock.domain.Stock;
//import org.junit.Assert;
//import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.data.redis.core.ReactiveRedisTemplate;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.test.annotation.Rollback;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import javax.annotation.Resource;
//import java.util.HashSet;
//import java.util.Set;
//
//import static org.hamcrest.CoreMatchers.is;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class CouponServiceTest {
//    @Resource
//    private RedisTemplate<String, Coupon> redisTemplate;
//
//    @Autowired
//    private ReactiveRedisTemplate<String, String> reactiveRedisTemplate;
//
//    @Autowired
//    CouponRepository couponRepository;
//
//    @Test
//    @Rollback(value = true)
//    public void createTest() {
//        // Given
//        CouponDTO couponDTO = new CouponDTO("coupon_test_1","쿠폰테스트1",10);
//        Coupon coupon = Coupon.Companion.of(couponDTO);
//
//        Stock stock = Stock.Companion.of(couponDTO,coupon);
//        coupon.setStock(stock);
//
//        // When
//        Coupon createdCoupon = couponRepository.save(coupon);
//
//        // Then
//        Assert.assertThat(createdCoupon.getCouponId(),is("coupon_test_1"));
//        Assert.assertThat(createdCoupon.getName(),is("쿠폰테스트1"));
//        Assert.assertThat(createdCoupon.getStock().getTotal(),is(10));
//    }
//
//    @Test
//    public void opsValue() {
//        reactiveRedisTemplate.opsForValue();
//        Set<String> cacheKeys = new HashSet<>();
//
////        reactiveRedisTemplate.
//    }
//
//}
