package com.jetbrains.gymusserverjava.memberships.dtos.requests

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.*
import org.springframework.web.multipart.MultipartFile
import java.time.LocalDate
import java.time.Period

@JvmRecord
data class RegisterMemberRequestDto(
    @NotBlank
    @NotNull
    @NotEmpty
    @Size(min = 3, max = 50)
    @JvmField
    @JsonProperty(value = "first_name")
    val firstName: String,

    @NotBlank
    @NotNull
    @NotEmpty
    @Size(min = 3, max = 50)
    @JvmField
    @JsonProperty("last_name")
    val lastName: String,

    @NotBlank
    @NotNull
    @NotEmpty
    @Email
    @JvmField
    val email: String,

    @NotBlank
    @NotNull
    @NotEmpty
    @Size(max = 25)
    @JvmField
    @JsonProperty(value = "phone_number")
    val phoneNumber: String,

    @NotNull
    @NotEmpty
    @NotBlank
    @JvmField val address: String,

    @NotNull
    @PastOrPresent
    @JvmField
    val birthdate: LocalDate,

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

    @NotNull
    @NotEmpty
    @NotBlank
    @JvmField
    @JsonProperty("membership_type")
    val membershipType: String
) {

    fun age(): Byte {
        return (Period.between(birthdate, LocalDate.now()).years.toByte())
    }
}
