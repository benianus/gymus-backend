package com.jetbrains.gymusserverjava.sessions.dtos.requests

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size

@JvmRecord
data class RegisterSessionRequestDto(
    @JvmField val fullName: @NotNull @NotBlank @NotEmpty @Size(min = 3) String,
    @JvmField val sessionTypeName: @NotNull @NotBlank @NotEmpty String
)
