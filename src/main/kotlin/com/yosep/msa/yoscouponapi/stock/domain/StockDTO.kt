package com.yosep.msa.yoscouponapi.stock.domain

import lombok.AllArgsConstructor
import lombok.Builder
import lombok.Data
import lombok.NoArgsConstructor
import javax.validation.constraints.Min
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
class StockDTO (
    @NotBlank
    var stockId:String,

    @NotNull
    @Min(value = 1)
    var total:Int
)