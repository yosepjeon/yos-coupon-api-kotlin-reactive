package com.yosep.msa.yoscouponapi.stock.domain

import com.yosep.msa.yoscouponapi.coupon.domain.BaseEntity
import com.yosep.msa.yoscouponapi.coupon.domain.Coupon
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
class Stock (
        @Id
        val stockId:String,

        @OneToOne(cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
        @JoinColumn(name = "couponId")
        val coupon: Coupon,

        @NotNull
        @Column(nullable = false)
        var total:Int,

        createdDate: LocalDateTime,
        lastModifiedBy: LocalDateTime
): BaseEntity(createdDate, lastModifiedBy)