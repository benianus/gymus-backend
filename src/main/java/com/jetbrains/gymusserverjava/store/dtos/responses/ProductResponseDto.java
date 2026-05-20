package com.jetbrains.gymusserverjava.store.dtos.responses;

import java.time.LocalDateTime;

public record ProductResponseDto(
        int id,
        String productName,
        String productImage,
        String productDescription,
        int quantity,
        double price,
        int addedBy,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {}
