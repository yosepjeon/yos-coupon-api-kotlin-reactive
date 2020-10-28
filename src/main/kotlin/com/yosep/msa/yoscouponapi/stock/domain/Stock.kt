package com.yosep.msa.yoscouponapi.stock.domain

import com.fasterxml.jackson.annotation.JsonBackReference
import com.yosep.msa.yoscouponapi.coupon.domain.BaseEntity
import com.yosep.msa.yoscouponapi.coupon.domain.Coupon
import com.yosep.msa.yoscouponapi.coupon.domain.CouponDTO
import com.yosep.msa.yoscouponapi.coupon.domain.CouponState
import lombok.EqualsAndHashCode
import lombok.Getter
import lombok.Setter
import lombok.ToString
import java.time.LocalDateTime
import java.util.*
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
        @OneToOne(cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
        @JoinColumn(name = "coupon_id")
        var coupon: Coupon,

        @NotNull
        @Column(nullable = false)
        var total:Int,

        override var createdDate: LocalDateTime,
        override var lastModifiedBy: LocalDateTime
): BaseEntity(createdDate, lastModifiedBy) {
        constructor(){}

        companion object {
                fun of(couponDTO: CouponDTO, coupon: Coupon):Stock {
                        val stock = Stock()
                        stock.stockId = couponDTO.couponId + "_stock"
                        stock.total = couponDTO.total
                        stock.coupon = coupon

                        return stock
                }
        }
}