package com.yosep.msa.yoscouponapi.coupon.domain

import com.yosep.msa.yoscouponapi.coupon.controller.CouponController
import org.springframework.hateoas.EntityModel
import org.springframework.hateoas.Link
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder


class CouponResource(coupon: Coupon, vararg links: Link?) : EntityModel<Coupon?>(coupon, *links) {
    init {
        add(WebMvcLinkBuilder.linkTo(CouponController::class.java).slash(coupon.couponId).withSelfRel())
    }
}