package com.yosep.msa.yoscouponapi.coupon.controller

import com.yosep.msa.yoscouponapi.common.ErrorsResource
import com.yosep.msa.yoscouponapi.coupon.domain.Coupon
import com.yosep.msa.yoscouponapi.coupon.domain.CouponDTO
import com.yosep.msa.yoscouponapi.coupon.domain.CouponResource
import com.yosep.msa.yoscouponapi.coupon.service.CouponService
import com.yosep.msa.yoscouponapi.exception.CouponNotFoundException
import org.modelmapper.ModelMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PagedResourcesAssembler
import org.springframework.hateoas.EntityModel
import org.springframework.hateoas.Link
import org.springframework.hateoas.MediaTypes
import org.springframework.hateoas.PagedModel
import org.springframework.hateoas.server.RepresentationModelAssembler
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder
import org.springframework.http.ResponseEntity
import org.springframework.validation.Errors
import org.springframework.web.bind.annotation.*
import java.net.URI
import java.util.*
import javax.validation.Valid


@RestController
@RequestMapping(value = ["/api/coupons"], produces = [MediaTypes.HAL_JSON_VALUE])
class CouponController {
    @Autowired
    lateinit var couponService: CouponService
    @Autowired
    lateinit var modelMapper: ModelMapper
    var controllerLinkBuilder: WebMvcLinkBuilder = WebMvcLinkBuilder.linkTo(CouponController::class.java)


    @GetMapping
    fun getCoupons(pageable: Pageable?, assembler: PagedResourcesAssembler<Coupon>): ResponseEntity<*>? {
        val page: Page<Coupon?> = this.couponService.getCoupons(pageable)
        var pagedResources: PagedModel<CouponResource> = assembler.toModel(page, RepresentationModelAssembler<Coupon, CouponResource> { o: Coupon ->
            val couponResource = CouponResource(o)
            couponResource.add(controllerLinkBuilder.withRel("get-orders"))
            couponResource.add(controllerLinkBuilder.slash(o.couponId).withRel("get-order"))
            couponResource.add(controllerLinkBuilder.slash(o.couponId).withRel("patch-order"))
            couponResource
        })
        pagedResources.add(Link("/docs/index.html#resources-coupons-list").withRel("profile"))
        return ResponseEntity.ok<PagedModel<CouponResource>>(pagedResources)
    }

    @GetMapping("/{id}")
    fun getCoupon(@PathVariable id: String?): ResponseEntity<*>? {
        val orderURI = controllerLinkBuilder.slash(id).toUri()
        return try {
            val optionalOrder: Optional<Coupon?> = couponService.getCoupon(id)
            if (!optionalOrder.isPresent()) {
                return ResponseEntity.notFound().build<Any>()
            }
            val coupon: Coupon = optionalOrder.get()
            var couponResource = CouponResource(coupon)
            couponResource.add(controllerLinkBuilder.withRel("get-orders"))
            couponResource.add(controllerLinkBuilder.slash(id).withRel("get-product"))
            couponResource.add(controllerLinkBuilder.slash(id).withRel("patch-order"))
            couponResource.add(Link("/docs/index.html#resources-order-get").withRel("profile"))
            ResponseEntity.ok<Any>(couponResource)
        } catch (e: CouponNotFoundException) {
            ResponseEntity.notFound().build<Any>()
        }
    }

    @PostMapping
    fun createCoupon(@RequestBody @Valid couponDTO: CouponDTO, errors: Errors): ResponseEntity<*>? {
        if(errors.hasErrors()) {
            return badRequest(errors)
        }


        var coupon: Coupon = couponService.create(couponDTO)
        var couponResource: CouponResource = CouponResource(coupon)
        couponResource.add(controllerLinkBuilder.withRel("get-coupons"))
        couponResource.add(controllerLinkBuilder.withRel("create-coupons"))
        couponResource.add(controllerLinkBuilder.slash(coupon.couponId).withRel("get-coupon"))
        couponResource.add(controllerLinkBuilder.slash(coupon.couponId).withRel("update-coupon"))
        couponResource.add(controllerLinkBuilder.slash(coupon.couponId).withRel("delete-coupon"))

        var createdURI:URI = controllerLinkBuilder.slash(coupon.couponId).toUri();

        return ResponseEntity.created(createdURI).body(couponResource)
    }

    private fun badRequest(errors: Errors): ResponseEntity<*>? {
        return ResponseEntity.badRequest().body<Any>(ErrorsResource(errors))
    }
}