package com.jetbrains.shared.utils

import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component
import java.time.LocalDate
import java.time.Period

@Component
object Helpers {
    fun getUserDetails(): UserDetails {
        return SecurityContextHolder.getContext().authentication?.let {
            it.principal as UserDetails?
        } ?: throw IllegalStateException("UserDetails not found")
    }

    fun calculateAge(birthdate: LocalDate): Int {
        return Period.between(birthdate, LocalDate.now()).years
    }


}
