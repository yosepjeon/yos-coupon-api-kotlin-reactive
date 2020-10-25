package com.yosep.msa.yoscouponapi.coupon.domain

import lombok.Getter
import lombok.ToString
import java.time.LocalDateTime
import javax.persistence.MappedSuperclass

@MappedSuperclass
@ToString
@Getter
open class BaseEntity (
        val createdDate: LocalDateTime,
        val lastModifiedBy: LocalDateTime
)