package com.jetbrains.gymusserverjava.memberships.dtos.responses

import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDateTime

@JvmRecord
data class RecentMemberChecksInDto(
    val id: Int,
    @JsonProperty("first_name")
    val firstName: String,
    @JsonProperty("last_name")
    val lastName: String,
    @JsonProperty("personal_photo")
    val personalPhoto: String,
    @JsonProperty("membership_type")
    val membershipType: String,
    val status: Boolean,
    @JsonProperty("checked_in_at")
    val checkedInAt: LocalDateTime
)
