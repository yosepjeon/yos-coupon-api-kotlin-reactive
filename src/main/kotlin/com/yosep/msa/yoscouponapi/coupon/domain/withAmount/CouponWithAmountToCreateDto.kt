package com.yosep.msa.yoscouponapi.coupon.domain.withAmount

import com.yosep.msa.yoscouponapi.coupon.domain.CouponDTO
import java.time.LocalDateTime
import javax.validation.Valid
import javax.validation.constraints.Min
import javax.validation.constraints.NotNull

data class CouponWithAmountToCreateDto(
        @NotNull
        override var couponName: String,

        @Valid
        @NotNull
        @Min(value = 0)
        var total: Int,

        override var startDate: String,
        override var endDate: String
) : CouponDTO() {

}