package com.yosep.msa.yoscouponapi.common.domain

import java.time.LocalDateTime
import javax.persistence.MappedSuperclass

@MappedSuperclass
abstract class BaseEntity {
    abstract val createdDate: LocalDateTime
    abstract var lastModifiedDate: LocalDateTime
}