package com.jetbrains.gymusserverjava.memberships.dtos.requests

import jakarta.validation.constraints.*
import org.springframework.web.multipart.MultipartFile

@JvmRecord
data class UpdateMemberRequestDto(
    @NotBlank
    @NotNull
    @NotEmpty
    @Email
    @JvmField val email: String,

    @NotBlank
    @NotNull
    @NotEmpty
    @Size(max = 25)
    @JvmField val phoneNumber: String,

    @NotNull
    @NotEmpty
    @NotBlank
    @JvmField val address: String,
    @JvmField val medicalCertificate: MultipartFile,
    @JvmField val birthCertificate: MultipartFile,
    @JvmField val personalPhoto: MultipartFile,
    @JvmField val parentalAuthorization: MultipartFile?,

    @NotNull
    @NotEmpty
    @NotBlank
    @JvmField val membershipType: String
)
