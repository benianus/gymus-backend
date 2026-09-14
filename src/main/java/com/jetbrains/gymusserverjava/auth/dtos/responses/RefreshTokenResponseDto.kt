package com.jetbrains.gymusserverjava.auth.dtos.responses

@JvmRecord
data class RefreshTokenResponseDto(
    val accessToken: String,
    val refreshToken: String
)
