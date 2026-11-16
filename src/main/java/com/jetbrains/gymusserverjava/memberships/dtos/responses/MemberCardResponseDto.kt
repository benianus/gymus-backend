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
    val personalPhoto: String,
    val checkDate: LocalDate?
) {
    val isActive: Boolean
        get() = endDate.isAfter(LocalDate.now())

    val isChecked: Boolean
        get() = checkDate?.isEqual(LocalDate.now()) ?: false
}