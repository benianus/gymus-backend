package com.jetbrains.gymusserverjava.auth.dtos.responses

import com.fasterxml.jackson.annotation.JsonProperty

data class RefreshTokenResponseDto(
    @JsonProperty(value = "access_token")
    val accessToken: String,
    @JsonProperty(value = "refresh_token")
    val refreshToken: String
)
