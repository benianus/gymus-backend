package com.jetbrains.gymusserverjava.sessions.dtos.requests

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size

@JvmRecord
data class RegisterSessionRequestDto(
    @JvmField
    @NotNull
    @NotBlank
    @NotEmpty
    @Size(min = 3)
    @JsonProperty("full_name")
    val fullName: String,

    @JvmField
    @NotNull
    @NotBlank
    @NotEmpty
    @JsonProperty("session_type")
    val sessionType: String
)
