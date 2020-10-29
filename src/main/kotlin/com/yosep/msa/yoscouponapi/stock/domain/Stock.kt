package com.yosep.msa.yoscouponapi.stock.domain

import com.fasterxml.jackson.annotation.JsonBackReference
import com.yosep.msa.yoscouponapi.coupon.domain.Coupon
import com.yosep.msa.yoscouponapi.coupon.domain.CouponDTO
import lombok.EqualsAndHashCode
import lombok.Getter
import lombok.Setter
import lombok.ToString
import java.time.LocalDateTime
import javax.persistence.*
import javax.validation.constraints.NotNull

@Entity
@Getter
@Setter
@ToString
@Table(name = "yos_coupon_stock")
@EqualsAndHashCode(of = ["stockId"], callSuper = false)
data class Stock (
        @Id
        var stockId:String,

        @JsonBackReference
        @OneToOne(cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
        @JoinColumn(name = "coupon_id")
        var coupon: Coupon,

        @NotNull
        @Column(nullable = false)
        var total:Int,

        var createdDate: LocalDateTime,
        var lastModifiedBy: LocalDateTime
) {

        companion object {
                fun of(couponDTO: CouponDTO, coupon: Coupon):Stock {
//                        val stock = Stock()
//                        stock.stockId = couponDTO.couponId + "_stock"
//                        stock.total = couponDTO.total
//                        stock.coupon = coupon

                        return Stock(couponDTO.couponId + "_stock",coupon,couponDTO.total,LocalDateTime.now(),LocalDateTime.now())
                }
        }
}