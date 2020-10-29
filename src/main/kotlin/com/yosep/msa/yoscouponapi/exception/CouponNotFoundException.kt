package com.yosep.msa.yoscouponapi.exception

class CouponNotFoundException(id: String) : RuntimeException("ID: " + id + "인 Order가 존재하지 않습니다...")