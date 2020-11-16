package com.yosep.msa.yoscouponapi.coupon.controller

import com.yosep.msa.yoscouponapi.coupon.domain.withAmount.CouponWithAmountToCreateDto
import com.yosep.msa.yoscouponapi.coupon.domain.resource.CouponResource
import com.yosep.msa.yoscouponapi.coupon.domain.withAmount.CouponWithAmountToUpdateDto
import com.yosep.msa.yoscouponapi.coupon.service.CouponWithAmountService
import org.modelmapper.ModelMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.hateoas.Link
import org.springframework.hateoas.MediaTypes
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder
import org.springframework.http.ResponseEntity
import org.springframework.validation.Errors
import org.springframework.web.bind.annotation.*
import java.net.URI
import javax.validation.Valid

@RestController
@RequestMapping(value = ["/api/coupons"], produces = [MediaTypes.HAL_JSON_VALUE + ";charset=UTF-8"])
class CouponWithAmountController {
    var couponWithAmountService: CouponWithAmountService
    var modelMapper: ModelMapper
    var controllerLinkBuilder: WebMvcLinkBuilder
    lateinit var couponResource: CouponResource

    @Autowired
    constructor(couponWithAmountService: CouponWithAmountService, modelMapper: ModelMapper) {

        this.couponWithAmountService = couponWithAmountService
        this.modelMapper = modelMapper
        this.controllerLinkBuilder = WebMvcLinkBuilder.linkTo(CouponWithAmountController::class.java)
    }

    @PostMapping
    fun createCoupon(@RequestBody @Valid couponDTO: CouponWithAmountToCreateDto, errors:Errors): ResponseEntity<Any> {
        if(errors.hasErrors()) {
            return badRequest(errors)
        }

        var createdCoupon = couponWithAmountService.create(couponDTO)
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

    @PatchMapping
    fun patchCoupon(@RequestBody @Valid couponWithAmountToUpdateDto: CouponWithAmountToUpdateDto, errors: Errors): ResponseEntity<Any> {
        if(errors.hasErrors()) {
            return badRequest(errors)
        }

        var updatedCoupon = couponWithAmountService.update(couponWithAmountToUpdateDto)
        couponResource = CouponResource(updatedCoupon)
        couponResource
                .add(controllerLinkBuilder.withRel("get-coupons"))
                .add(controllerLinkBuilder.slash(updatedCoupon.couponId).withRel("get-coupon"))
                .add(controllerLinkBuilder.slash(updatedCoupon.couponId).withRel("patch-coupon"))
                .add(controllerLinkBuilder.slash(updatedCoupon.couponId).withRel("put-coupon"))
                .add(Link.of("/docs/index.html#create-coupon").withRel("profile"))

        return ResponseEntity.ok(couponResource)
    }

    @DeleteMapping(value = ["/{couponId}"])
    fun deleteCouponById(@PathVariable(value = "couponId") couponId:String): ResponseEntity<Any>{
        try {
            couponWithAmountService.deleteById(couponId)
            var map:MutableMap<String, Any> = HashMap()
            map.put("couponId", couponId)
            return ResponseEntity.ok(map)
        }catch (e: Exception) {
            return ResponseEntity.notFound().build()
        }
    }

    private fun badRequest(errors: Errors): ResponseEntity<Any> {
        return ResponseEntity.notFound().build()
    }
}
