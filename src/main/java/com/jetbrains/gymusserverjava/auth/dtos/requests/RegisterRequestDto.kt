package com.jetbrains.gymusserverjava.auth.dtos.requests

import jakarta.validation.constraints.NotBlank


data class RegisterRequestDto(
    @NotBlank
    val username: String,
    @NotBlank
    val password: String
)
