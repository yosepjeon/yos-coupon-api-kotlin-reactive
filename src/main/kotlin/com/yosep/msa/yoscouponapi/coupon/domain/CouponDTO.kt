package com.yosep.msa.yoscouponapi.coupon.domain

import javax.validation.Valid
import javax.validation.constraints.Min
import javax.validation.constraints.NotNull

data class CouponDTO (
    @NotNull
    var name:String,

    @Valid
    @NotNull
    @Min(value = 1)
    var total: Int
)