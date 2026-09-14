package com.jetbrains.gymusserverjava.auth.dtos.requests

import jakarta.validation.constraints.NotBlank

@JvmRecord
data class LoginRequestDto(
    @NotBlank val username: String,
    @NotBlank val password: String
)
