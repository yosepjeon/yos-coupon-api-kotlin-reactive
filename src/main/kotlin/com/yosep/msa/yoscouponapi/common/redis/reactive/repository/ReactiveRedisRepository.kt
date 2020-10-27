package com.yosep.msa.yoscouponapi.common.redis.reactive.repository

import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.time.Duration

interface ReactiveRedisRepository<T, ID> {
    fun save(entity: T): Mono<Boolean>

    fun saveAll(entities: Iterable<T>): Mono<Boolean>

    fun findById(id:ID): Mono<T>

    fun findAllById(id:Iterable<ID>): Flux<T>

    fun deleteById(id: ID): Mono<Long>

    fun deleteAllById(ids: Iterable<ID>): Mono<Long>

    fun increase(id: ID, value: Long): Mono<Long>

    fun decrease(id: ID, value: Long): Mono<Long>;

    fun expireById(id:ID, duration:Duration):Mono<Boolean>
}