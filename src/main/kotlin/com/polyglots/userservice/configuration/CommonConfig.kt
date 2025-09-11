package com.polyglots.userservice.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain

@Configuration
class CommonConfig {

    @Bean
    fun passwordEncoder(): PasswordEncoder = BCryptPasswordEncoder()

    @Bean
    @Deprecated("Temporarily disabled authentication to make all existing endpoints open while implementing the application")
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .csrf { it.disable() } // disable CSRF for APIs
            .authorizeHttpRequests { auth ->
                auth.anyRequest().permitAll() // allow everything
            }
        return http.build()
    }

}