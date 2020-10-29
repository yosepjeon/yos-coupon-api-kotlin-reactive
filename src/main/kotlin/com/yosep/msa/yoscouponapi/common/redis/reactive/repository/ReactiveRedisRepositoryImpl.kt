package com.yosep.msa.yoscouponapi.common.redis.reactive.repository

import org.springframework.data.redis.core.ReactiveRedisTemplate
import org.springframework.data.redis.core.ReactiveValueOperations
import org.springframework.data.redis.core.RedisTemplate
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.time.Duration

class ReactiveRedisRepositoryImpl<T,ID,V>: ReactiveRedisRepository<T, ID> {
    private val redisTemplate:RedisTemplate<ID,V>
    private val reactiveRedisTemplate:ReactiveRedisTemplate<ID,V>
    private val reactiveValueOperations: ReactiveValueOperations<ID,V>
    constructor(reactiveRedisTemplate: ReactiveRedisTemplate<ID, V>, reactiveValueOperations: ReactiveValueOperations<ID, V>, redisTemplate: RedisTemplate<ID, V>) {
        this.reactiveRedisTemplate = reactiveRedisTemplate
        this.reactiveValueOperations = reactiveValueOperations
        this.redisTemplate = redisTemplate
    }

    override fun save(entity: T): Mono<Boolean> {
        TODO("Not yet implemented")
    }

    override fun saveAll(entities: Iterable<T>): Mono<Boolean> {
        TODO("Not yet implemented")
    }

    override fun findById(id: ID): Mono<T> {
        TODO("Not yet implemented")
    }

    override fun findAllById(id: Iterable<ID>): Flux<T> {
        TODO("Not yet implemented")
    }

    override fun deleteById(id: ID): Mono<Long> {
        return reactiveRedisTemplate.delete(id)
    }

    override fun deleteAllById(ids: Iterable<ID>): Mono<Long> {
        return reactiveRedisTemplate.delete(Flux.fromIterable(ids))
    }

    override fun increase(id: ID, value: Long): Mono<Long> {
        return reactiveValueOperations.increment(id,value)
    }

    override fun decrease(id: ID, value: Long): Mono<Long> {
//        if(reactiveValueOperations.get(id!!).equals("0")) {
//            return Integer.parseInt()reactiveValueOperations.get(id!!)
//        }

        return reactiveValueOperations.decrement(id,value)
    }

    override fun expireById(id: ID, duration: Duration): Mono<Boolean> {
        return reactiveRedisTemplate.expire(id,duration)
    }


}