package com.yosep.msa.yoscouponapi.coupon.domain.resource

import com.yosep.msa.yoscouponapi.coupon.controller.CouponWithAmountController
import com.yosep.msa.yoscouponapi.coupon.domain.Coupon
import org.springframework.hateoas.EntityModel
import org.springframework.hateoas.Link
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder

class CouponCreatedResource(val coupon: Coupon, vararg links: Link?): EntityModel<Coupon>(coupon, *links) {
    init {
        add(WebMvcLinkBuilder.linkTo(CouponWithAmountController::class.java).slash(coupon.couponId).withSelfRel())
    }
}