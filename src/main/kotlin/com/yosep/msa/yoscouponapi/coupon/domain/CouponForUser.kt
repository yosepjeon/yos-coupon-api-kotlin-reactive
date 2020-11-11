package com.yosep.msa.yoscouponapi.coupon.domain

import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "yoggaebi_coupon_for_user")
class CouponForUser(
        override val couponId: String,
        override var couponName: String,
        override var couponState: CouponState,

        @Column(nullable = false)
        var total: Int,

        override val createdDate: LocalDateTime,
        override var lastModifiedDate: LocalDateTime
) : Coupon(couponId, couponName, couponState, createdDate, lastModifiedDate) {

}