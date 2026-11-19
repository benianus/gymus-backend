package com.jetbrains.gymusserverjava.store.dtos.requests

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.web.multipart.MultipartFile

@JvmRecord
data class UpdateProductRequestDto(
    @JvmField
    @JsonProperty("product_name")
    val productName: String,
    @JvmField
    @JsonProperty("product_image")
    val productImage: MultipartFile,
    @JvmField
    @JsonProperty("product_description")
    val productDescription: String,
    @JvmField
    val quantity: Int,
    @JvmField
    val price: Double
)
