package com.jetbrains.gymusserverjava.store.dtos.requests;

import org.springframework.web.multipart.MultipartFile;

public record UpdateProductRequestDto(
        String productName,
        MultipartFile productImage,
        String productDescription,
        int quantity,
        double price
) {}
