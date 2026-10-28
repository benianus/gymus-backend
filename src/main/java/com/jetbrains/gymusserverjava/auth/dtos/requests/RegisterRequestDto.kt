package com.jetbrains.gymusserverjava.auth.dtos.requests

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull


@JvmRecord
data class RegisterRequestDto(
    @NotBlank
    @NotNull
    val username: String,

    @NotNull
    @NotBlank
    val password: String
)
