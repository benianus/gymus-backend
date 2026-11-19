package com.jetbrains.shared.dtos

import com.fasterxml.jackson.annotation.JsonProperty

data class PagedResponse<T>(
    val data: T,
    @JsonProperty("page_number")
    val pageNumber: Int,
    @JsonProperty("page_size")
    val pageSize: Int,
    @JsonProperty("total_items")
    val totalItems: Long,
    @JsonProperty("total_pages")
    val totalPages: Int,
    @JsonProperty("has_next")
    val hasNext: Boolean,
    @JsonProperty("has_previous")
    val hasPrevious: Boolean
)
