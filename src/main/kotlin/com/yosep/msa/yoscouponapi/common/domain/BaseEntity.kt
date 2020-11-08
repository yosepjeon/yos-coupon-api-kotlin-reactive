package com.yosep.msa.yoscouponapi.common.domain

import java.time.LocalDateTime

open class BaseEntity (){
    open val createdDate: LocalDateTime? = null
    open var updatedDate: LocalDateTime? = null
}