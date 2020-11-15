package com.yosep.msa.yoscouponapi.exception

import java.lang.RuntimeException

class CouponNotFoundException(
        var id:String
): RuntimeException("ID: $id 인 Coupon이 존재하지 않습니다...") {

}