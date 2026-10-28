package com.jetbrains.gymusserverjava.auth.dtos.responses

data class RefreshTokenResponseDto(
    val accessToken: String,
    val refreshToken: String
)
