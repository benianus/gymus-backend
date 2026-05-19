// 2026-04-12T13:11:56.685Z (logged at)


package com.jetbrains.shared.security

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "jwt")
data class JwtProperties(
    val key: String,
    val accessTokenExpiration: Long,
    val refreshTokenExpiration: Long
)
