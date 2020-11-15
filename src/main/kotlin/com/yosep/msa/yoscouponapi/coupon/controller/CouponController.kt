package com.yosep.msa.yoscouponapi.coupon.controller

import com.yosep.msa.yoscouponapi.coupon.domain.withAmount.CouponWithAmountToCreateDto
import com.yosep.msa.yoscouponapi.coupon.domain.resource.CouponResource
import com.yosep.msa.yoscouponapi.coupon.service.CouponWithAmountService
import org.modelmapper.ModelMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.hateoas.Link
import org.springframework.hateoas.MediaTypes
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder
import org.springframework.http.ResponseEntity
import org.springframework.validation.Errors
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.net.URI
import javax.validation.Valid

@RestController
@RequestMapping(value = ["/api/coupons"], produces = [MediaTypes.HAL_JSON_VALUE + ";charset=UTF-8"])
class CouponController {
    var couponWithAmountService: CouponWithAmountService
    var modelMapper: ModelMapper
    var controllerLinkBuilder: WebMvcLinkBuilder
    lateinit var couponResource: CouponResource

    @Autowired
    constructor(couponWithAmountService: CouponWithAmountService, modelMapper: ModelMapper) {

        this.couponWithAmountService = couponWithAmountService
        this.modelMapper = modelMapper
        this.controllerLinkBuilder = WebMvcLinkBuilder.linkTo(CouponController::class.java)
    }

    @PostMapping
    fun createCoupon(@RequestBody @Valid couponDTO: CouponWithAmountToCreateDto, errors:Errors): ResponseEntity<Any> {
        if(errors.hasErrors()) {
            return badRequest(errors)
        }

        var createdCoupon = couponWithAmountService.createCouponWithAmount(couponDTO)
        couponResource = CouponResource(createdCoupon)
        couponResource
                .add(controllerLinkBuilder.withRel("get-coupons"))
                .add(controllerLinkBuilder.slash(createdCoupon.couponId).withRel("get-coupon"))
                .add(controllerLinkBuilder.slash(createdCoupon.couponId).withRel("patch-coupon"))
                .add(controllerLinkBuilder.slash(createdCoupon.couponId).withRel("put-coupon"))
                .add(Link.of("/docs/index.html#create-coupon").withRel("profile"))


        var createdURI: URI = controllerLinkBuilder.slash(createdCoupon.couponId).toUri()

        return ResponseEntity.created(createdURI).body(couponResource)
    }

    private fun badRequest(errors: Errors): ResponseEntity<Any> {
        return ResponseEntity.notFound().build()
    }
}
