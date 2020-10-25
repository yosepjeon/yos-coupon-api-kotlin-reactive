package com.yosep.msa.yoscouponapi.coupon.domain

interface CouponSaleStrategy {
    fun discount(): Int
}