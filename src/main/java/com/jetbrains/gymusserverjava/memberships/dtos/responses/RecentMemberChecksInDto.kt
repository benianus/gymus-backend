package com.jetbrains.gymusserverjava.memberships.dtos.responses

import java.time.LocalDateTime

@JvmRecord
data class RecentMemberChecksInDto(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val personalPhoto: String,
    val membershipType: String,
    val status: Boolean,
    val checkedInAt: LocalDateTime
)
