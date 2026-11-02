package com.jetbrains.gymusserverjava.memberships.dtos.requests

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.Size
import org.springframework.web.multipart.MultipartFile

@JvmRecord
data class UpdateMemberRequestDto(
    @NotBlank
    @NotEmpty
    @Email
    @JvmField val email: String,

    @NotBlank
    @NotEmpty
    @Size(max = 25)
    @JvmField val phoneNumber: String,

    @NotEmpty
    @NotBlank
    @JvmField val address: String,
    @JvmField val medicalCertificate: MultipartFile,
    @JvmField val birthCertificate: MultipartFile,
    @JvmField val personalPhoto: MultipartFile,
    @JvmField val parentalAuthorization: MultipartFile?,

    @NotEmpty
    @NotBlank
    @JvmField val membershipType: String
)
