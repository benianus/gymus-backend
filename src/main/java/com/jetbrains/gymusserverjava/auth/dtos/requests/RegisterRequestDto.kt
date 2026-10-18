package com.jetbrains.gymusserverjava.auth.dtos.requests

import jakarta.validation.constraints.NotBlank


@JvmRecord
data class RegisterRequestDto(
    @NotBlank
    val username: String,
    @NotBlank
    val password: String
)
