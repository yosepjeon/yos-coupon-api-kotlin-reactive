package com.yosep.msa.yoscouponapi.coupon.domain


import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonManagedReference
import com.yosep.msa.yoscouponapi.stock.domain.Stock
import lombok.*
import java.time.LocalDateTime
import java.util.*
import javax.persistence.*
import javax.validation.constraints.NotNull

@Entity
@Getter
@Setter
@ToString
@Table(name = "yos_coupon")
@Builder
@EqualsAndHashCode(of = ["couponId"], callSuper = false)
data class Coupon(
        @Id
        var couponId:String,

        @NotNull
        @Column(nullable = false)
        var name:String,

//        @NotNull
//        @Column(nullable = false)
//        val userId:String,

        @NotNull
        @Enumerated(EnumType.STRING)
        @Column(length = 10, nullable = false)
        var state: CouponState,

        @JsonManagedReference
        @OneToOne(mappedBy = "coupon", cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
        var stock: Stock,

        override var createdDate: LocalDateTime,
        override var lastModifiedBy: LocalDateTime
): BaseEntity(createdDate, lastModifiedBy) {
    constructor(){}

    companion object {
        fun of(couponDTO:CouponDTO):Coupon {
            val coupon:Coupon = Coupon()
            coupon.couponId = UUID.randomUUID().toString()
            coupon.name = couponDTO.name
            coupon.state = CouponState.ENABLE
            coupon.createdDate = LocalDateTime.now()
            coupon.lastModifiedBy = LocalDateTime.now()

            return coupon
        }
    }

}
