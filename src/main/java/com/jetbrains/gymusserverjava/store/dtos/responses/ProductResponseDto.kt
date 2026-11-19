package com.jetbrains.gymusserverjava.store.dtos.responses

import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDateTime

@JvmRecord
data class ProductResponseDto(
    val id: Int,
    @JsonProperty("product_name")
    val productName: String,
    @JsonProperty("product_image")
    val productImage: String,
    @JsonProperty("product_description")
    val productDescription: String,
    val quantity: Int,
    val price: Double,
    @JsonProperty("added_by")
    val addedBy: Int,
    @JsonProperty("created_at")
    val createdAt: LocalDateTime,
    @JsonProperty("updated_at")
    val updatedAt: LocalDateTime
)
