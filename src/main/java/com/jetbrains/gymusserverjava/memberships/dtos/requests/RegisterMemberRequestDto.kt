package com.jetbrains.gymusserverjava.memberships.dtos.requests

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
    @JvmField val firstName: String,

    @NotBlank
    @NotNull
    @NotEmpty
    @Size(min = 3, max = 50)
    @JvmField val lastName: String,

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

    @NotNull
    @PastOrPresent
    @JvmField val birthdate: LocalDate,
    @JvmField val medicalCertificate: MultipartFile,
    @JvmField val birthCertificate: MultipartFile,
    @JvmField val personalPhoto: MultipartFile,
    @JvmField val parentalAuthorization: MultipartFile?,

    @NotNull
    @NotEmpty
    @NotBlank
    @JvmField val membershipType: String
) {

    fun age(): Byte {
        return (Period.between(birthdate, LocalDate.now()).years.toByte())
    }
}
