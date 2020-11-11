package com.yosep.msa.yoscouponapi.config

import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler


@EnableResourceServer
@Configuration
class ResourceServerConfig : ResourceServerConfigurerAdapter() {
    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {
        // TODO Auto-generated method stub
        http.headers().frameOptions().disable()
        http
                .csrf().disable()
                .anonymous()
                .and()
                .authorizeRequests()
                .mvcMatchers(HttpMethod.POST).permitAll()
                .mvcMatchers(HttpMethod.GET,"/h2-console").permitAll()
                .mvcMatchers(HttpMethod.GET, "/api/coupons").permitAll() //				.mvcMatchers(HttpMethod.GET,"/user/test")
                //					.anonymous()
                .anyRequest().permitAll()
                .and()
                .exceptionHandling()
                .accessDeniedHandler(OAuth2AccessDeniedHandler())
    }
}