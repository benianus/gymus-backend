package com.jetbrains.gymusserverjava.memberships.dtos.responses

import java.time.LocalDate
import java.time.LocalDateTime

data class MemberResponseDto(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val email: String,
    val phoneNumber: String,
    val address: String,
    val birthdate: LocalDate,
    val personalPhoto: String,
    val endDate: LocalDate,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
) {
    val isActive: Boolean
        get() = endDate.isAfter(LocalDate.now())
}
