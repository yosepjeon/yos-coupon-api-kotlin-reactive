package com.yosep.msa.yoscouponapi.coupon.domain.withAmount

import com.yosep.msa.yoscouponapi.coupon.domain.CouponDTO
import com.yosep.msa.yoscouponapi.coupon.domain.CouponState
import java.time.LocalDateTime
import javax.validation.Valid
import javax.validation.constraints.Min
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

data class CouponWithAmountToUpdateDto(
        @NotNull
        val couponId: String,

        @NotBlank
        override var couponName: String,

        @Valid
        @NotNull
        @Min(value=0)
        var total: Int,

        var couponState: CouponState,

        @NotNull
        override var startDate: String,
        @NotNull
        override var endDate: String
) : CouponDTO() {

}