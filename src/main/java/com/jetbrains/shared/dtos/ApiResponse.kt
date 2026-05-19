package com.jetbrains.shared.dtos

data class ApiResponse<T>(val data: T?, val status: String, val errors: List<String>?) {
    constructor(data: T) : this(data, "success", null)
    constructor(errors: List<String>) : this(null, "error", errors)
}
