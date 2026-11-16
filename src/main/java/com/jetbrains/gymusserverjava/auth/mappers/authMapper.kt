package com.jetbrains.gymusserverjava.auth.mappers

import com.jetbrains.gymusserverjava.auth.dtos.requests.RegisterRequestDto
import com.jetbrains.gymusserverjava.auth.entities.User

fun RegisterRequestDto.toEntity() =
    User().apply {
        username = this@toEntity.username
        password = this@toEntity.password
    }