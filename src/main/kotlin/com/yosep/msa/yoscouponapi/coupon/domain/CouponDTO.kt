package com.yosep.msa.yoscouponapi.coupon.domain

import java.time.LocalDateTime
import javax.validation.Valid
import javax.validation.constraints.Min
import javax.validation.constraints.NotNull

abstract class CouponDTO {
    open lateinit var couponName: String
    open lateinit var startDate: String
    open lateinit var endDate: String
}

