package com.yosep.msa.yoscouponapi.coupon.domain.withAmount

import com.yosep.msa.yoscouponapi.coupon.domain.Coupon
import com.yosep.msa.yoscouponapi.coupon.domain.CouponState
import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "yoggaebi_coupon_for_user")
data class CouponWithAmount(
        override val couponId: String,
        override var couponName: String,
        override var couponState: CouponState,

        @Column(nullable = false)
        var total: Int,

        override var startDate: LocalDateTime,
        override var endDate: LocalDateTime,

        override val createdDate: LocalDateTime,
        override var lastModifiedDate: LocalDateTime
) : Coupon(couponId, couponName, couponState, startDate, endDate, createdDate, lastModifiedDate) {

}