package com.yosep.msa.yoscouponapi.common

import org.springframework.boot.test.autoconfigure.restdocs.RestDocsMockMvcConfigurationCustomizer

import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentationConfigurer
import org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint


@TestConfiguration
class RestDocsConfiguration {
    @Bean
    fun restDocsMockMvcConfigurationCustomizer(): RestDocsMockMvcConfigurationCustomizer {
        return RestDocsMockMvcConfigurationCustomizer { configurer: MockMvcRestDocumentationConfigurer ->
            configurer.operationPreprocessors().withRequestDefaults(prettyPrint())
                    .withResponseDefaults(prettyPrint())
        }
    }
}