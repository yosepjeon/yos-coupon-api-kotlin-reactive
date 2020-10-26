package com.yosep.msa.yoscouponapi.coupon.domain

import com.yosep.msa.yoscouponapi.stock.domain.Stock
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
@Table(name = "yos_coupon")
@EqualsAndHashCode(of = ["couponId"], callSuper = false)
class Coupon(
        @Id
        val couponId:String,

        @NotNull
        @Column(nullable = false)
        val name:String,

        @NotNull
        @Column(nullable = false)
        val userId:String,

        @NotNull
        @Enumerated(EnumType.STRING)
        @Column(length = 10, nullable = false)
        val state: CouponState,

        @OneToOne(cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
        @JoinColumn(name = "stockId")
        val stock: Stock,

        createdDate: LocalDateTime,
        lastModifiedBy: LocalDateTime
): BaseEntity(createdDate, lastModifiedBy) {


}