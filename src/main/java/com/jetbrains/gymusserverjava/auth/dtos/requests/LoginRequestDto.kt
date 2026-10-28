package com.jetbrains.gymusserverjava.auth.dtos.requests

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

@JvmRecord
data class LoginRequestDto(
    @NotBlank
    @NotNull
    val username: String,

    @NotNull
    @NotBlank
    val password: String
)
