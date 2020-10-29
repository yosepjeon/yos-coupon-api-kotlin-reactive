package com.yosep.msa.yoscouponapi.stock.service

import com.yosep.msa.yoscouponapi.common.redis.reactive.repository.ReactiveRedisRepositoryImpl
import com.yosep.msa.yoscouponapi.stock.domain.Stock
import com.yosep.msa.yoscouponapi.stock.repository.StockRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.redis.core.ReactiveRedisTemplate
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.ServerResponse.ok
import org.springframework.web.reactive.function.server.body
import reactor.core.publisher.Mono
import reactor.core.publisher.switchIfEmpty
import reactor.kotlin.core.publisher.switchIfEmpty

@Component
class StockHandler(private val stockRepository: StockRepository) {
    lateinit var reactiveRedisRepository: ReactiveRedisRepositoryImpl<Stock,String, String>

    @Autowired
    lateinit var reactiveRedisTemplate: ReactiveRedisTemplate<String, String>

    fun haveRestCoupon(req:ServerRequest): Mono<ServerResponse> {
        val body: Mono<ServerResponse> = ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(reactiveRedisTemplate.opsForValue().decrement(req.pathVariable("id"))).switchIfEmpty(Mono.empty()))


        return ServerResponse.ok().body(body,Long::class.java)
    }


}