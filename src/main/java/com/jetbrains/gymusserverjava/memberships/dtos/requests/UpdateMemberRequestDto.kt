package com.jetbrains.gymusserverjava.memberships.dtos.requests

import com.fasterxml.jackson.annotation.JsonProperty
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
    @JvmField
    @JsonProperty("phone_number")
    val phoneNumber: String,

    @NotEmpty
    @NotBlank
    @JvmField
    val address: String,
    @JvmField
    @JsonProperty("medical_certificate")
    val medicalCertificate: MultipartFile,

    @JvmField
    @JsonProperty("birth_certificate")
    val birthCertificate: MultipartFile,

    @JvmField
    @JsonProperty("personal_photo")
    val personalPhoto: MultipartFile,

    @JvmField
    @JsonProperty("parental_authorization")
    val parentalAuthorization: MultipartFile?,

    @NotEmpty
    @NotBlank
    @JvmField
    @JsonProperty("membership_type")
    val membershipType: String
)
