package com.yosep.msa.yoscouponapi.coupon.service

import com.yosep.msa.yoscouponapi.coupon.domain.Coupon
import com.yosep.msa.yoscouponapi.coupon.domain.CouponDTO
import com.yosep.msa.yoscouponapi.coupon.repository.CouponRepository
import lombok.extern.slf4j.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Slf4j
@Service
@Transactional(readOnly = true)
class CouponService {
    val couponRepository:CouponRepository

    @Autowired
    constructor(couponRepository: CouponRepository) {
        this.couponRepository = couponRepository
    }

    @Transactional(readOnly = false)
    public fun create(couponDTO:CouponDTO) {
//        var coupon =

//        return Coupon("1")
    }
}