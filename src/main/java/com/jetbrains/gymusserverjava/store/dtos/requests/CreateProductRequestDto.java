package com.jetbrains.gymusserverjava.store.dtos.requests;

import org.springframework.web.multipart.MultipartFile;

public record CreateProductRequestDto(
        String productName,
        MultipartFile productImage,
        String productDescription,
        int quantity,
        double price
) {}
