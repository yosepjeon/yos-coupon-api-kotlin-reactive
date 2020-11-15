package com.yosep.msa.yoscouponapi.coupon.domain

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonManagedReference
import com.yosep.msa.yoscouponapi.common.domain.BaseEntity
import com.yosep.msa.yoscouponapi.stock.domain.Stock
import java.time.LocalDateTime
import javax.persistence.*
import javax.validation.constraints.NotNull
import kotlin.jvm.Transient

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn
abstract class Coupon(
        @Id
        val couponId: String,

        @NotNull
        @Column(nullable = false)
        var couponName:String,

        @NotNull
        @Enumerated(EnumType.STRING)
        @Column(length = 10, nullable = false)
        var couponState:CouponState,

//        @Enumerated(EnumType.STRING)
//        @Column(nullable = true)
//        var couponType

//        @JsonManagedReference
//        @OneToOne
//        var stock: Stock,

        @Column(nullable = false)
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        var startDate:LocalDateTime,

        @Column(nullable = false)
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        var endDate: LocalDateTime,

        @Column(nullable = false)
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        override val createdDate:LocalDateTime,

        @Column(nullable = false)
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        override var lastModifiedDate: LocalDateTime

): BaseEntity() {

}