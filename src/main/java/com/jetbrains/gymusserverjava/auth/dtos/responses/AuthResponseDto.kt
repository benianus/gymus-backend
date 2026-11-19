package com.jetbrains.gymusserverjava.auth.dtos.responses

import com.fasterxml.jackson.annotation.JsonProperty

data class AuthResponseDto(
    @JsonProperty(value = "access_token")
    val accessToken: String?,
    @JsonProperty(value = "refresh_token")
    val refreshToken: String?,
    val username: String?,
    val role: String?,
    @JsonProperty(value = "expires_in")
    val expiresIn: Long
) 