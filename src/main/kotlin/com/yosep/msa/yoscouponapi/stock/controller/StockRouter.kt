package com.yosep.msa.yoscouponapi.stock.controller

import com.yosep.msa.yoscouponapi.stock.service.StockHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.RequestPredicates.POST
import org.springframework.web.reactive.function.server.RequestPredicates.path
import org.springframework.web.reactive.function.server.RouterFunctions.nest
import org.springframework.web.reactive.function.server.router


@Configuration
class StockRouter(private val handler: StockHandler) {
    @Bean
    fun routerFunction() = nest(path("/api/coupon-stock"),
        router {
            listOf(
                    POST("/{id}",handler::haveRestCoupon)
            )
        }
    )
}