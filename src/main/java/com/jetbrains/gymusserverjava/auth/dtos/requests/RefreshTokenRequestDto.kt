package com.jetbrains.gymusserverjava.auth.dtos.requests

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

@JvmRecord
data class RefreshTokenRequestDto(
    @NotBlank
    @NotNull
    @JsonProperty(value = "refresh_token")
    val refreshToken: String,

    @NotBlank
    @NotNull
    val username: String
)
