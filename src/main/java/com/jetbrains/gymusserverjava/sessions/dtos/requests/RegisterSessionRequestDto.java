package com.jetbrains.gymusserverjava.sessions.dtos.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record RegisterSessionRequestDto(
        @NotNull
        @NotBlank
        @NotEmpty
        @Size(min = 3)
        String fullName,
        @NotNull
        @NotBlank
        @NotEmpty
        String sessionTypeName
) {
}
