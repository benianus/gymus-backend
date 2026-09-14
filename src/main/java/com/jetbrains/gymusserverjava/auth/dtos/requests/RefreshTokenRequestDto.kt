package com.jetbrains.gymusserverjava.auth.dtos.requests

@JvmRecord
data class RefreshTokenRequestDto(val refreshToken: String, val username: String)
