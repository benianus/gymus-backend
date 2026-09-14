package com.jetbrains.gymusserverjava.memberships.dtos.responses

import java.time.LocalDate

data class MemberCardResponseDto(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val birthdate: LocalDate,
    val joinDate: LocalDate,
    val startDate: LocalDate,
    val endDate: LocalDate,
    val personalPhoto: String
) {
    val isActive: Boolean
        get() = endDate.isAfter(LocalDate.now())
}