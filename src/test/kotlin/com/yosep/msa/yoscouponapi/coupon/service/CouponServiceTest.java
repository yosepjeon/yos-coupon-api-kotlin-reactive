package com.yosep.msa.yoscouponapi.coupon.service;

import com.yosep.msa.yoscouponapi.coupon.domain.Coupon;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles(value = "test")
public class CouponServiceTest {
    @Resource
    private RedisTemplate<String, Coupon> redisTemplate;

    @Autowired
    private ReactiveRedisTemplate<String, String> reactiveRedisTemplate;

    @Test
    public void opsValue() {
        reactiveRedisTemplate.opsForValue();
        Set<String> cacheKeys = new HashSet<>();

//        reactiveRedisTemplate.
    }

}
