package com.yosep.msa.yoscouponapi.coupon.domain


import com.fasterxml.jackson.annotation.JsonManagedReference
import com.yosep.msa.yoscouponapi.stock.domain.Stock
import java.time.LocalDateTime
import java.util.*
import javax.persistence.*
import javax.validation.constraints.NotNull

@Entity
@Table(name = "yos_coupon")
data class Coupon(
        @Id
        var couponId:String,

        @NotNull
        @Column(nullable = false)
        var name:String,

        @NotNull
        @Enumerated(EnumType.STRING)
        @Column(length = 10, nullable = false)
        var state: CouponState,

        @JsonManagedReference
        @OneToOne(mappedBy = "coupon", cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
        var stock: Stock?,

        var createdDate: LocalDateTime,
        var lastModifiedBy: LocalDateTime
) {

    companion object {
        fun of(couponDTO:CouponDTO):Coupon {
            val coupon:Coupon = Coupon(UUID.randomUUID().toString(),couponDTO.name,CouponState.START,null,LocalDateTime.now(),LocalDateTime.now())
            coupon.couponId = UUID.randomUUID().toString()
            coupon.name = couponDTO.name
            coupon.state = CouponState.START
            coupon.createdDate = LocalDateTime.now()
            coupon.lastModifiedBy = LocalDateTime.now()

            return coupon
        }
    }


}
