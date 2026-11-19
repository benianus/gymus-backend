package com.jetbrains.gymusserverjava.memberships.dtos.responses

import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDate
import java.time.LocalDateTime

data class MemberResponseDto(
    val id: Int,
    @JsonProperty("first_name")
    val firstName: String,
    @JsonProperty("last_name")
    val lastName: String,
    val email: String,
    @JsonProperty("phone_number")
    val phoneNumber: String,
    val address: String,
    val birthdate: LocalDate,
    @JsonProperty("personal_photo")
    val personalPhoto: String,
    @JsonProperty("end_date")
    val endDate: LocalDate,
    @JsonProperty("created_at")
    val createdAt: LocalDateTime,
    @JsonProperty("updated_at")
    val updatedAt: LocalDateTime
) {
    val isActive: Boolean
        get() = endDate.isAfter(LocalDate.now())
}
