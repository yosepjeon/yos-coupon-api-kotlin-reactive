package com.yosep.msa.yoscouponapi.coupon.repository

import com.yosep.msa.yoscouponapi.coupon.domain.Coupon
import org.springframework.data.jpa.repository.JpaRepository

interface CouponRepository : JpaRepository<Coupon, String> {

}