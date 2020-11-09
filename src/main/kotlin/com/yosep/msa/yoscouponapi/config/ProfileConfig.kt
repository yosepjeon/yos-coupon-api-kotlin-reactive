package com.yosep.msa.yoscouponapi.config

import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import
import kotlin.reflect.KClass

@Import(value = [ProfileDev::class, ProfileProd::class])
@Configuration
class ProfileConfig {

}