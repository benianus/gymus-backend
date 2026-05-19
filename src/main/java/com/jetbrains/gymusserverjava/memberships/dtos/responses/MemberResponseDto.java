package com.jetbrains.gymusserverjava.memberships.dtos.responses;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record MemberResponseDto(
        int id,
        String firstName,
        String lastName,
        String email,
        String phoneNumber,
        String address,
        LocalDate birthdate,
        String personalPhoto,
        LocalDate endDate,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {

    public boolean isActive() {
        return endDate.isAfter(LocalDate.now());
    }

}
