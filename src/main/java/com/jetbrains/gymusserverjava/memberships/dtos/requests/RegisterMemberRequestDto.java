package com.jetbrains.gymusserverjava.memberships.dtos.requests;

import jakarta.validation.constraints.*;
import org.jspecify.annotations.NonNull;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.Period;

public record RegisterMemberRequestDto(
        @NotBlank
        @NotNull
        @NotEmpty
        @Size(min = 3, max = 50)
        String firstName,
        @NotBlank
        @NotNull
        @NotEmpty
        @Size(min = 3, max = 50)
        String lastName,
        @NotBlank
        @NotNull
        @NotEmpty
        @Email
        String email,
        @NotBlank
        @NotNull
        @NotEmpty
        @Size(max = 25)
        String phoneNumber,
        @NotNull
        @NotEmpty
        @NotBlank
        String address,
        @NotNull
        @PastOrPresent
        LocalDate birthdate,
        @NonNull MultipartFile medicalCertificate,
        @NonNull MultipartFile birthCertificate,
        @NonNull MultipartFile personalPhoto,
        MultipartFile parentalAuthorization,
        @NotNull
        @NotEmpty
        @NotBlank
        String membershipType
) {

    public byte age() {
        return ((byte) Period.between(birthdate, LocalDate.now()).getYears());
    }

}
