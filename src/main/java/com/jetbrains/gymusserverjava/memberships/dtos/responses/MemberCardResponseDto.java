package com.jetbrains.gymusserverjava.memberships.dtos.responses;

import java.time.LocalDate;

public record MemberCardResponseDto(
        int id,
        String firstName,
        String lastName,
        LocalDate birthdate,
        LocalDate joinDate,
        LocalDate endDate,
        String personalPhoto
) {

    public boolean isActive() {
        return endDate.isAfter(LocalDate.now());
    }

}