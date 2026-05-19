package com.jetbrains.gymusserverjava.auth.dtos.responses;

import java.util.List;

public record AuthResponseDto(
        String accessToken,
        String refreshToken,
        String username,
        String role,
        Long expiresIn
) {
}