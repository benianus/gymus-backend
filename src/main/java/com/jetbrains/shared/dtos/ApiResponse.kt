package com.jetbrains.shared.dtos

sealed interface ApiResponse<out D, out E> {
    data class Success<out D, out E>(val data: D) : ApiResponse<D, E>
    data class Error<out D, out E>(val error: E) : ApiResponse<D, E>
}