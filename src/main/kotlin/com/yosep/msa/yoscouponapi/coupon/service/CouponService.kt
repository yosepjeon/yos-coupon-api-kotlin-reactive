package com.yosep.msa.yoscouponapi.coupon.service

import com.yosep.msa.yoscouponapi.coupon.domain.Coupon
import com.yosep.msa.yoscouponapi.coupon.domain.CouponDTO
import com.yosep.msa.yoscouponapi.coupon.repository.CouponRepository
import com.yosep.msa.yoscouponapi.stock.repository.StockRepository
import lombok.extern.slf4j.Slf4j
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Slf4j
@Service
@Transactional(readOnly = true)
class CouponService {
    private val couponRepository:CouponRepository
    private val stockRepository:StockRepository

    constructor(couponRepository: CouponRepository, stockRepository: StockRepository) {
        this.couponRepository = couponRepository
        this.stockRepository = stockRepository
    }

    @Transactional(readOnly = false)
    public fun create(couponDTO: CouponDTO): Coupon {
        var coupon = Coupon.of(couponDTO)
        val createdCoupon = couponRepository.save(coupon)
//        val stoc
    }
}