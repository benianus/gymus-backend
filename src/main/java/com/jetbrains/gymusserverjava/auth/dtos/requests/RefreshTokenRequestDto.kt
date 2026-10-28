package com.jetbrains.gymusserverjava.auth.dtos.requests

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

@JvmRecord
data class RefreshTokenRequestDto(
    @NotBlank
    @NotNull
    val refreshToken: String,

    @NotBlank
    @NotNull
    val username: String
)
