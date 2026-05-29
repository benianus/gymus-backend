package com.jetbrains.shared.security

import com.jetbrains.shared.enums.Role
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
class SecurityConfig(
    private val jwtFilter: JwtFilter,
    private val userDetailsService: UserDetailsService
) {
    @Bean
    @Throws(Exception::class)
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain? =
        http.redirectToHttps { it.disable() }
            .cors { it.disable() }
            .csrf { it.disable() }
            .authorizeHttpRequests {
                it
                    // auth
                    .requestMatchers("/api/auth/**")
                    .permitAll() // memberships & members
                    .requestMatchers(HttpMethod.GET, "/api/memberships/members")
                    .hasAnyRole(Role.OWNER.name, Role.EMPLOYEE.name)
                    .requestMatchers(HttpMethod.GET, "/api/memberships/members/{memberId}")
                    .hasAnyRole(Role.OWNER.name, Role.EMPLOYEE.name, Role.MEMBER.name)
                    .requestMatchers(HttpMethod.POST, "/api/memberships/register")
                    .hasAnyRole(Role.OWNER.name)
                    .requestMatchers(
                        HttpMethod.POST,
                        "/api/memberships/record/{memberId}/attendance"
                    )
                    .hasAnyRole(Role.OWNER.name, Role.EMPLOYEE.name)
                    .requestMatchers(HttpMethod.POST, "/api/memberships/{memberId}/renew")
                    .hasAnyRole(Role.OWNER.name, Role.EMPLOYEE.name)
                    .requestMatchers(HttpMethod.PUT, "/api/memberships/members/{memberId}")
                    .hasAnyRole(Role.OWNER.name)
                    .requestMatchers(
                        HttpMethod.DELETE,
                        "/api/memberships/members/{memberId}"
                    )
                    .hasAnyRole(Role.OWNER.name)
                    // sessions
                    .requestMatchers(HttpMethod.GET, "/api/sessions")
                    .hasAnyRole(Role.OWNER.name, Role.EMPLOYEE.name)
                    .requestMatchers(HttpMethod.POST, "/api/sessions")
                    .hasAnyRole(Role.OWNER.name)
                    // store
                    .requestMatchers(HttpMethod.GET, "/api/products")
                    .hasAnyRole(Role.OWNER.name, Role.EMPLOYEE.name, Role.MEMBER.name)
                    .requestMatchers(HttpMethod.GET, "/api/products/{productId}")
                    .hasAnyRole(Role.OWNER.name, Role.EMPLOYEE.name, Role.MEMBER.name)
                    .requestMatchers(HttpMethod.POST, "/api/products")
                    .hasAnyRole(Role.OWNER.name)
                    .requestMatchers(HttpMethod.PUT, "/api/products/{productId}")
                    .hasAnyRole(Role.OWNER.name)
                    .requestMatchers(HttpMethod.DELETE, "/api/products/{productId}")
                    .hasAnyRole(Role.OWNER.name)
                    .requestMatchers(HttpMethod.POST, "/api/products/{productId}/sales")
                    .hasAnyRole(Role.OWNER.name, Role.EMPLOYEE.name)
                    // reports
                    .requestMatchers("/api/reports/**")
                    .permitAll()
                    .anyRequest()
                    .authenticated()
            }
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter::class.java)
            .authenticationManager(authenticationManager(http))
            .build()


    @Bean
    fun passwordEncoder(): PasswordEncoder = BCryptPasswordEncoder()

    @Bean
    @Throws(Exception::class)
    fun authenticationManager(httpSecurity: HttpSecurity): AuthenticationManager? {
        val authBuilder =
            httpSecurity.getSharedObject(AuthenticationManagerBuilder::class.java)
        authBuilder.userDetailsService<UserDetailsService?>(userDetailsService)
            .passwordEncoder(passwordEncoder())
        return authBuilder.build()
    }

    @Bean
    fun corsConfigurationSource(): CorsConfigurationSource {
        val corsConfiguration = CorsConfiguration()

        corsConfiguration.allowedOrigins = listOf("*")
        corsConfiguration.allowedMethods = listOf("*")
        corsConfiguration.allowedHeaders = listOf("*")
        corsConfiguration.allowCredentials = true

        val source = UrlBasedCorsConfigurationSource()
        source.registerCorsConfiguration("/**", corsConfiguration)
        return source
    }
}
