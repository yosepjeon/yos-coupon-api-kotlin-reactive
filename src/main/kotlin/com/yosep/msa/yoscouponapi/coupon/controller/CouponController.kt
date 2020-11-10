package com.yosep.msa.yoscouponapi.coupon.controller

import com.yosep.msa.yoscouponapi.coupon.domain.Coupon
import com.yosep.msa.yoscouponapi.coupon.domain.CouponDTO
import com.yosep.msa.yoscouponapi.coupon.domain.resource.CouponResource
import com.yosep.msa.yoscouponapi.coupon.service.CouponService
import org.modelmapper.ModelMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.hateoas.MediaTypes
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder
import org.springframework.http.ResponseEntity
import org.springframework.validation.Errors
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping(value = ["/api/coupons"], produces = [MediaTypes.HAL_JSON_VALUE])
class CouponController {
    var couponService: CouponService
    var modelMapper: ModelMapper
    var controllerLinkBuilder: WebMvcLinkBuilder
    lateinit var couponResource: CouponResource

    @Autowired
    constructor(couponService: CouponService, modelMapper: ModelMapper) {

        this.couponService = couponService
        this.modelMapper = modelMapper
        this.controllerLinkBuilder = WebMvcLinkBuilder.linkTo(CouponController::class.java)
    }

    @PostMapping(value = ["/"])
    fun createCoupon(@RequestBody @Valid couponDTO: CouponDTO, errors:Errors) {
//        if(errors.hasErrors()) {
//            return badRequest(errors)
//        }

//        System.out.println(coupon.toString());
    }

    private fun badRequest(errors: Errors): ResponseEntity<Any> {
        return ResponseEntity.notFound().build()
    }
}
