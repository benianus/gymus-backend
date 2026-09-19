package com.jetbrains.gymusserverjava.store.dtos.responses

import java.time.LocalDateTime

@JvmRecord
data class ProductResponseDto(
    val id: Int,
    val productName: String,
    val productImage: String,
    val productDescription: String,
    val quantity: Int,
    val price: Double,
    val addedBy: Int,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
)
