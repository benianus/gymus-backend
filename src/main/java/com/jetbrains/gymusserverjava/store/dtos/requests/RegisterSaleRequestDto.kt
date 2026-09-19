package com.jetbrains.gymusserverjava.store.dtos.requests

@JvmRecord
data class RegisterSaleRequestDto(
    @JvmField val quantity: Int,
    @JvmField val totalPrice: Double
)
