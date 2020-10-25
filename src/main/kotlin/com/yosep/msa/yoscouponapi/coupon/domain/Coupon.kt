package com.yosep.msa.yoscouponapi.coupon.domain

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
class Coupon(
        @Id
        val id:String,

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

        createdDate: LocalDateTime,
        lastModifiedBy: LocalDateTime
): BaseEntity(createdDate, lastModifiedBy) {


}