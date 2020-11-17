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
import java.util.*
import javax.validation.Valid
import kotlin.collections.HashMap

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

    @GetMapping("/{couponId}")
    fun findCouponById(@PathVariable("couponId") couponId:String):ResponseEntity<Any> {
        val findedCoupon = couponWithAmountService.findCouponById(couponId)

        if(findedCoupon.isEmpty) {
            return ResponseEntity.notFound().build()
        }else {
            var couponId = findedCoupon.get().couponId
            println("finded coupon = $findedCoupon")
            couponResource = CouponResource(findedCoupon.get())
            couponResource
//                    .add(controllerLinkBuilder.withRel("get-coupon"))
                    .add(controllerLinkBuilder.slash(couponId).withRel("create-coupon"))
                    .add(controllerLinkBuilder.slash(couponId).withRel("get-coupons"))
                    .add(controllerLinkBuilder.slash(couponId).withRel("get-coupon"))
                    .add(controllerLinkBuilder.slash(couponId).withRel("patch-coupon"))
                    .add(controllerLinkBuilder.slash(couponId).withRel("put-coupon"))
                    .add(controllerLinkBuilder.slash(couponId).withRel("delete-coupon"))
                    .add(Link.of("/docs/index.html#get-coupon").withRel("profile"))

            return ResponseEntity.ok().body(couponResource)
        }
    }

    @PostMapping
    fun createCoupon(@RequestBody @Valid couponDTO: CouponWithAmountToCreateDto, errors:Errors): ResponseEntity<Any> {
        if(errors.hasErrors()) {
            return badRequest(errors)
        }

        var createdCoupon = couponWithAmountService.create(couponDTO)
        couponResource = CouponResource(createdCoupon)
        couponResource
//                .add(controllerLinkBuilder.withRel("create-coupon"))
                .add(controllerLinkBuilder.slash(createdCoupon.couponId).withRel("create-coupon"))
                .add(controllerLinkBuilder.slash(createdCoupon.couponId).withRel("get-coupons"))
                .add(controllerLinkBuilder.slash(createdCoupon.couponId).withRel("get-coupon"))
                .add(controllerLinkBuilder.slash(createdCoupon.couponId).withRel("patch-coupon"))
                .add(controllerLinkBuilder.slash(createdCoupon.couponId).withRel("put-coupon"))
                .add(controllerLinkBuilder.slash(createdCoupon.couponId).withRel("delete-coupon"))
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
//                .add(controllerLinkBuilder.withRel("patch-coupon"))
                .add(controllerLinkBuilder.slash(updatedCoupon.couponId).withRel("create-coupon"))
                .add(controllerLinkBuilder.slash(updatedCoupon.couponId).withRel("get-coupons"))
                .add(controllerLinkBuilder.slash(updatedCoupon.couponId).withRel("get-coupon"))
                .add(controllerLinkBuilder.slash(updatedCoupon.couponId).withRel("patch-coupon"))
                .add(controllerLinkBuilder.slash(updatedCoupon.couponId).withRel("put-coupon"))
                .add(controllerLinkBuilder.slash(updatedCoupon.couponId).withRel("delete-coupon"))
                .add(Link.of("/docs/index.html#patch-coupon").withRel("profile"))

        return ResponseEntity.ok(couponResource)
    }

    @DeleteMapping(value = ["/{couponId}"])
    fun deleteCouponById(@PathVariable(value = "couponId") couponId:String): ResponseEntity<Any>{
        try {
            couponWithAmountService.deleteById(couponId)
            var map:MutableMap<String, Any> = HashMap()
            map.put("couponId", couponId)
            return ResponseEntity.ok().build()
        }catch (e: Exception) {
            return ResponseEntity.notFound().build()
        }
    }

    private fun badRequest(errors: Errors): ResponseEntity<Any> {
        return ResponseEntity.notFound().build()
    }
}
