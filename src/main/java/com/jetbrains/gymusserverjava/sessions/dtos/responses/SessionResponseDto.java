package com.jetbrains.gymusserverjava.sessions.dtos.responses;

import java.time.LocalDateTime;

public record SessionResponseDto(
        int id,
        String fullName,
        String sessionTypeName,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {}
