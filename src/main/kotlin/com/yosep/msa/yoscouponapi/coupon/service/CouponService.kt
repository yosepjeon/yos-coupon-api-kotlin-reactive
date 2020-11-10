package com.yosep.msa.yoscouponapi.coupon.service

import com.yosep.msa.yoscouponapi.coupon.domain.*
import com.yosep.msa.yoscouponapi.coupon.repository.CouponRepository
import lombok.extern.slf4j.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime
import java.util.*

@Slf4j
@Service
@Transactional(readOnly = true)
class CouponService {
    val couponRepository:CouponRepository

    @Autowired
    constructor(couponRepository: CouponRepository) {
        this.couponRepository = couponRepository
    }

    @Transactional(readOnly = false)
    public fun createCouponForUser(couponDTO:CouponDtoToCreate): CouponForUser {
        var couponId:String
        var findedCoupon: Optional<Coupon>

        do {
            couponId = "coupon_" + UUID.randomUUID()
            var findedCoupon = couponRepository.findById(couponId)
        }while (!findedCoupon.isEmpty)

        var coupon = CouponForUser(
                couponId,
                couponDTO.couponName,
                CouponState.START,
                couponDTO.total,
                LocalDateTime.now(),
                LocalDateTime.now()
        )

        return couponRepository.save(coupon)
    }
}