package com.yosep.msa.yoscouponapi.coupon.domain

import java.util.function.Supplier

enum class CouponState(state: String) {
    WAIT("WAIT"),
    USED("USED"),
    EXPIRED("EXPIRED")
}