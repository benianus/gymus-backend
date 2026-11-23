package com.jetbrains.gymusserverjava.memberships.dtos.responses

import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDate

data class MemberCardResponseDto(
    @JsonProperty("member_card_id")
    val memberCardId: Int,
    @JsonProperty("member_id")
    val memberId: Int,
    @JsonProperty("first_name")
    val firstName: String,
    @JsonProperty("last_name")
    val lastName: String,
    val birthdate: LocalDate,
    @JsonProperty("join_date")
    val joinDate: LocalDate,
    @JsonProperty("start_date")
    val startDate: LocalDate,
    @JsonProperty("end_date")
    val endDate: LocalDate,
    @JsonProperty("personal_photo")
    val personalPhoto: String,
    @JsonProperty("check_date")
    val checkDate: LocalDate?,
    @JsonProperty("last_renewal")
    val lastRenewal: LocalDate?
) {
    val isActive: Boolean
        get() = endDate.isAfter(LocalDate.now())

    val isChecked: Boolean
        get() = checkDate?.isEqual(LocalDate.now()) ?: false
}