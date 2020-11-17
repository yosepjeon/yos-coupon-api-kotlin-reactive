package com.yosep.msa.yoscouponapi.coupon.service

import com.yosep.msa.yoscouponapi.common.util.parseToLocalDateTime
import com.yosep.msa.yoscouponapi.coupon.domain.*
import com.yosep.msa.yoscouponapi.coupon.domain.withAmount.CouponWithAmount
import com.yosep.msa.yoscouponapi.coupon.domain.withAmount.CouponWithAmountToCreateDto
import com.yosep.msa.yoscouponapi.coupon.domain.withAmount.CouponWithAmountToUpdateDto
import com.yosep.msa.yoscouponapi.coupon.repository.CouponRepository
import com.yosep.msa.yoscouponapi.coupon.repository.CouponWithAmountRepository
import com.yosep.msa.yoscouponapi.exception.CouponNotFoundException
import lombok.extern.slf4j.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

@Slf4j
@Service
@Transactional(readOnly = true)
class CouponWithAmountService {
    val couponRepository: CouponRepository
    val couponWithAmountRepository: CouponWithAmountRepository

    @Autowired
    constructor(couponRepository: CouponRepository, couponWithAmountRepository: CouponWithAmountRepository) {
        this.couponRepository = couponRepository
        this.couponWithAmountRepository = couponWithAmountRepository
    }

    public fun findCouponById(couponId: String):Optional<CouponWithAmount> {
        return couponWithAmountRepository.findById(couponId)
    }

    /*
    * logic
    * 1.쿠폰 id를 생성한다.
    * 2.생성한 아이디를 조회해서 해당 아이디를 갖는 쿠폰이 있는지 조회한다.
    * 3.해당 아이디가 pk 요건을 충족할때까지 반복한다. 충족하면 루프 탈출
    * 4.couponDTO로부터 data를 가져와 coupon객체를 생성한다.
    * 5.해당 쿠폰 객체를 DB에 저장한다.
     */
    @Transactional(readOnly = false)
    public fun create(couponDTO: CouponWithAmountToCreateDto): CouponWithAmount {
        var couponId: String
        var findedCoupon: Optional<Coupon>

        do {
            couponId = "coupon_" + UUID.randomUUID()
            findedCoupon = couponRepository.findById(couponId)
        } while (!findedCoupon.isEmpty)

        var coupon = CouponWithAmount(
                couponId,
                couponDTO.couponName,
                CouponState.START,
                couponDTO.total,
                parseToLocalDateTime(couponDTO.startDate, "yyyy-MM-dd HH:mm:ss"),
                parseToLocalDateTime(couponDTO.endDate, "yyyy-MM-dd HH:mm:ss"),
                LocalDateTime.now(),
                LocalDateTime.now()
        )

        return couponRepository.save(coupon)
    }

    @Transactional(readOnly = false)
    public fun update(couponWithAmountToUpdateDto: CouponWithAmountToUpdateDto): CouponWithAmount {
        val couponId = couponWithAmountToUpdateDto.couponId
        var findedCouponOptional = couponWithAmountRepository.findById(couponId)

        if (findedCouponOptional.isEmpty)
            throw CouponNotFoundException(couponId)

        var findedCoupon = findedCouponOptional.get()

        if (couponWithAmountToUpdateDto.couponName != null) {

        }

        findedCoupon.couponName = couponWithAmountToUpdateDto.couponName
        findedCoupon.couponState = couponWithAmountToUpdateDto.couponState
        findedCoupon.total = couponWithAmountToUpdateDto.total
        findedCoupon.lastModifiedDate = LocalDateTime.now()
        findedCoupon.startDate = LocalDateTime.parse(couponWithAmountToUpdateDto.startDate,DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
        findedCoupon.endDate = LocalDateTime.parse(couponWithAmountToUpdateDto.endDate,DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))

        return couponWithAmountRepository.save(findedCoupon)
    }

    @Transactional(readOnly = false)
    public fun deleteById(couponId:String) {
        couponWithAmountRepository.deleteById(couponId)
    }

    public fun syncRedisToDataBase() {

    }
}