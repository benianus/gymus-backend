package com.jetbrains.shared.dtos

data class PagedResponse<T>(
    val data: T,
    val pageNumber: Int,
    val pageSize: Int,
    val totalItems: Long,
    val totalPages: Int,
    val hasNext: Boolean,
    val hasPrevious: Boolean
)
