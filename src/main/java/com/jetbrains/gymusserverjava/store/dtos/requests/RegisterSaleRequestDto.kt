package com.jetbrains.gymusserverjava.store.dtos.requests

import com.fasterxml.jackson.annotation.JsonProperty

@JvmRecord
data class RegisterSaleRequestDto(
    @JvmField val quantity: Int,
    @JsonProperty("total_price")
    @JvmField val totalPrice: Double
)
