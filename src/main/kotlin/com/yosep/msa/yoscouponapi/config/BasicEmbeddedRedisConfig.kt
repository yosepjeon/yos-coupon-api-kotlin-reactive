package com.yosep.msa.yoscouponapi.config//package com.yosep.msa.yoscouponapi.config
//
//import org.springframework.beans.factory.annotation.Value
//import org.springframework.context.annotation.Configuration
//import redis.embedded.RedisServer
//import java.io.IOException
//import javax.annotation.PostConstruct
//import javax.annotation.PreDestroy
//
//@Configuration
//class BasicEmbeddedRedisConfig {
//    @Value("\${spring.redis.port}")
//    private val redisPort: Int = 6379
//
//    private var redisServer: RedisServer? = null
//
//    @PostConstruct
//    @Throws(IOException::class)
//    fun redisServer() {
//        redisServer = RedisServer(redisPort)
//        redisServer!!.start()
//    }
//
//    @PreDestroy
//    fun stopRedis() {
//        if (redisServer != null) {
//            redisServer!!.stop()
//        }
//    }
//}