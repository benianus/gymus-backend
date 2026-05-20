package com.jetbrains.gymusserverjava.store.dtos.requests;

public record RegisterSaleRequestDto(
        int quantity,
        double totalPrice
) {}
