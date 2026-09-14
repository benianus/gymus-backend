package com.jetbrains.gymusserverjava.auth.dtos.responses

@JvmRecord
data class AuthResponseDto(
    val accessToken: String?,
    val refreshToken: String?,
    val username: String?,
    val role: String?,
    val expiresIn: Long?
) 