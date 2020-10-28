package com.yosep.msa.yoscouponapi.coupon.domain

import com.yosep.msa.yoscouponapi.stock.domain.Stock
import lombok.AllArgsConstructor
import lombok.Builder
import lombok.Data
import lombok.NoArgsConstructor
import javax.validation.Valid
import javax.validation.constraints.Min
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

@Data
@NoArgsConstructor
@AllArgsConstructor
data class CouponDTO (

    var couponId:String,

    @NotNull
    var name:String,

    @Valid
    @NotNull
    @Min(value = 1)
    var total:Int

)