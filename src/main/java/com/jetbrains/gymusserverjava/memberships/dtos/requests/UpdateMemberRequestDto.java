package com.jetbrains.gymusserverjava.memberships.dtos.requests;

import jakarta.validation.constraints.*;
import org.springframework.web.multipart.MultipartFile;

public record UpdateMemberRequestDto(
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
        MultipartFile medicalCertificate,
        MultipartFile birthCertificate,
        MultipartFile personalPhoto,
        MultipartFile parentalAuthorization,
        @NotNull
        @NotEmpty
        @NotBlank
        String membershipType
) {
}
