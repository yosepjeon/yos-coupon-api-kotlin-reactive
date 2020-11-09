package com.yosep.msa.yoscouponapi.config

import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.context.annotation.PropertySource

@Configuration
@Profile(value= ["dev"])
@PropertySource("classpath:dev/application.properties")
class ProfileDev {
}