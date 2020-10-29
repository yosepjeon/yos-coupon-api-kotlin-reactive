package com.yosep.msa.yoscouponapi.coupon.service

import com.yosep.msa.yoscouponapi.coupon.domain.Coupon
import com.yosep.msa.yoscouponapi.coupon.repository.CouponRepository
import jdk.nashorn.internal.runtime.regexp.joni.Config.log
import lombok.extern.slf4j.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.redis.core.ReactiveRedisTemplate
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service


@Service
@Slf4j
class CouponScheduledService {
    @Autowired
    lateinit var couponRepository:CouponRepository

    @Autowired
    lateinit var reactiveRedisTemplate: ReactiveRedisTemplate<String, String>

    @Scheduled(fixedDelay = 5000)
    fun syncRedisToMysqlAboutCouponStock() {
        var coupons:List<Coupon> = couponRepository.findAll()

        coupons.forEach { it -> {
            val key = it.couponId
            val stringStringValueOperations = reactiveRedisTemplate.opsForValue()
//
            var total: Int = Integer.parseInt(stringStringValueOperations.get(key).toString())
            it.stock!!.total = total
            couponRepository.save(it)
            }
        }
    }
}