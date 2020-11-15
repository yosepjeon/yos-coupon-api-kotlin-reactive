package com.yosep.msa.yoscouponapi.coupon.repository

import com.yosep.msa.yoscouponapi.coupon.domain.withAmount.CouponWithAmount
import org.springframework.data.jpa.repository.JpaRepository

interface CouponWithAmountRepository: JpaRepository<CouponWithAmount, String> {
}