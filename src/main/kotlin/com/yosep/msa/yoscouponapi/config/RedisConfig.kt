package com.yosep.msa.yoscouponapi.config

import io.lettuce.core.ClientOptions
import org.springframework.boot.autoconfigure.data.redis.LettuceClientConfigurationBuilderCustomizer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration
import org.springframework.data.redis.core.ReactiveRedisTemplate
import org.springframework.data.redis.core.ReactiveStringRedisTemplate
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer
import org.springframework.data.redis.serializer.RedisSerializationContext
import org.springframework.data.redis.serializer.RedisSerializer
import org.springframework.data.redis.serializer.StringRedisSerializer
import io.lettuce.core.ReadFrom.REPLICA_PREFERRED
import io.lettuce.core.TimeoutOptions
import io.lettuce.core.cluster.ClusterClientOptions
import io.lettuce.core.cluster.ClusterTopologyRefreshOptions
import java.time.Duration

@Configuration
class RedisConfig {

    @Bean
    fun reactiveStringRedistemplate(connectionFactory: ReactiveRedisConnectionFactory): ReactiveStringRedisTemplate {
        val serializer: RedisSerializer<String> = StringRedisSerializer()
        val jackson2JsonRedisSerializer = Jackson2JsonRedisSerializer(String::class.java)
        val serializationContext = RedisSerializationContext
                .newSerializationContext<String,String>()
                .key(serializer)
                .value(jackson2JsonRedisSerializer)
                .hashKey(serializer)
                .hashValue(jackson2JsonRedisSerializer)
                .build()

        return ReactiveStringRedisTemplate(connectionFactory, serializationContext)
    }

    @Bean
    fun stringLongReactiveRedisTemplate(connectionFactory: ReactiveRedisConnectionFactory): ReactiveRedisTemplate<String, Long> {
        val serializer: RedisSerializer<String> = StringRedisSerializer()
        val jackson2JsonRedisSerializer = Jackson2JsonRedisSerializer(Long::class.java)
        val serializationContext = RedisSerializationContext
                .newSerializationContext<String,Long>()
                .key(serializer)
                .value(jackson2JsonRedisSerializer)
                .hashKey(serializer)
                .hashValue(jackson2JsonRedisSerializer)
                .build()

        return ReactiveRedisTemplate<String, Long>(connectionFactory, serializationContext)
    }

    @Bean
    fun lettuceClientConfigurationBuilderCustomizer(): LettuceClientConfiguration.LettuceClientConfigurationBuilder {

        return LettuceClientConfiguration.builder()
                .readFrom(REPLICA_PREFERRED)
                .commandTimeout(Duration.ofSeconds(1L))
                .clientOptions(getClientOptions())
//        return LettuceClientConfiguration.builder()
//                .readFrom(REPLICA_PREFERRED)
//                .commandTimeout(Duration.ofSeconds(1L))
//                .clientOptions(getClientOptions())
    }

    fun getClientOptions(): ClientOptions {
        return ClusterClientOptions.builder()
                .topologyRefreshOptions(ClusterTopologyRefreshOptions.enabled())
                .autoReconnect(true)
                .timeoutOptions(TimeoutOptions.builder().fixedTimeout(Duration.ofSeconds(1L)).build())
                .build()
    }
}