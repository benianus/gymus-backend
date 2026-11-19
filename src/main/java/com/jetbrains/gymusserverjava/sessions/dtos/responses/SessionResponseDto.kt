package com.jetbrains.gymusserverjava.sessions.dtos.responses

import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDateTime

@JvmRecord
data class SessionResponseDto(
    val id: Int,
    @JsonProperty("full_name")
    val fullName: String?,
    @JsonProperty("session_type")
    val sessionType: String?,
    @JsonProperty("created_at")
    val createdAt: LocalDateTime?,
    @JsonProperty("updated_at")
    val updatedAt: LocalDateTime?
)
