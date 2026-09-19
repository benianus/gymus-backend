package com.jetbrains.gymusserverjava.store.dtos.requests

import org.springframework.web.multipart.MultipartFile

@JvmRecord
data class CreateProductRequestDto(
    @JvmField val productName: String,
    @JvmField val productImage: MultipartFile,
    @JvmField val productDescription: String,
    @JvmField val quantity: Int,
    @JvmField val price: Double
)

