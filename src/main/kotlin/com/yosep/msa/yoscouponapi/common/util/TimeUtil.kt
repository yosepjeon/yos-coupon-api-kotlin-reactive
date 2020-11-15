package com.yosep.msa.yoscouponapi.common.util

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun parseToLocalDateTime(time:String,pattern:String): LocalDateTime {
    return LocalDateTime.parse(time, DateTimeFormatter.ofPattern(pattern))
}