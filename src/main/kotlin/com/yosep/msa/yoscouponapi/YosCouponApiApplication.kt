package com.yosep.msa.yoscouponapi

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableScheduling
class YosCouponApiApplication

fun main(args: Array<String>) {
	runApplication<YosCouponApiApplication>(*args)
}
