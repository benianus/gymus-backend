package com.jetbrains.gymusserverjava.sessions.dtos.responses

import java.time.LocalDateTime

@JvmRecord
data class SessionResponseDto(
    val id: Int,
    val fullName: String?,
    val sessionTypeName: String?,
    val createdAt: LocalDateTime?,
    val updatedAt: LocalDateTime?
)
