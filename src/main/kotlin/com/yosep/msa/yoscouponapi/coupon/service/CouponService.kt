package com.yosep.msa.yoscouponapi.coupon.service

import com.yosep.msa.yoscouponapi.coupon.domain.Coupon
import com.yosep.msa.yoscouponapi.coupon.domain.CouponDTO
import com.yosep.msa.yoscouponapi.coupon.repository.CouponRepository
import com.yosep.msa.yoscouponapi.stock.domain.Stock
import com.yosep.msa.yoscouponapi.stock.repository.StockRepository
import lombok.extern.slf4j.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.redis.core.ReactiveRedisTemplate
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Slf4j
@Service
@Transactional(readOnly = true)
class CouponService {
    private val couponRepository:CouponRepository
    private val stockRepository:StockRepository
    @Autowired
    lateinit var reactiveRedisTemplate: ReactiveRedisTemplate<String, String>;

    constructor(couponRepository: CouponRepository, stockRepository: StockRepository) {
        this.couponRepository = couponRepository
        this.stockRepository = stockRepository
    }

    @Transactional(readOnly = false)
    public fun create(couponDTO: CouponDTO): Coupon {
        var coupon = Coupon.of(couponDTO)
//        val createdCoupon = couponRepository.save(coupon)

        var stock = Stock.of(couponDTO, coupon)
        coupon.stock = stock

        val key = coupon.couponId
        val stringStringValueOperations = reactiveRedisTemplate.opsForValue()

        stringStringValueOperations.set(key,"1")

        return couponRepository.save(coupon)
    }

    fun getCoupons(pageable: Pageable?): Page<Coupon?> {
        val page: Page<Coupon?> = couponRepository.findAll(pageable!!)
        return page
    }

    fun getCoupon(id: String?): Optional<Coupon?> {
        var findedCoupon:Optional<Coupon> = couponRepository.findById(id!!)
        var stock: Stock? = findedCoupon.get().stock

        val stringStringValueOperations = reactiveRedisTemplate.opsForValue()
        val result = stringStringValueOperations.get(id!!)
        if(result == null) stringStringValueOperations.set(id, "" + stock!!.total)

        return couponRepository.findById(id!!)
    }
}