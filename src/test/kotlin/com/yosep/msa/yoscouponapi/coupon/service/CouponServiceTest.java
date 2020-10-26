package com.yosep.msa.yoscouponapi.coupon.service;

import com.yosep.msa.yoscouponapi.coupon.domain.Coupon;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles(value = "test")
public class CouponServiceTest {
    @Resource
    private RedisTemplate<String, Coupon> redisTemplate;
}
