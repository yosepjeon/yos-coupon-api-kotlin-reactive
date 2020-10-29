package com.yosep.msa.yoscouponapi.stock.controller

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.RouterFunctions.nest

@Configuration
class StockRouter {
    @Bean
    fun routerFunction() = nest("/api/coupon-stock"),
        rou
}