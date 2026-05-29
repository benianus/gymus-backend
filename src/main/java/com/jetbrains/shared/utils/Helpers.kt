package com.jetbrains.shared.utils

import com.jetbrains.shared.dtos.PagedResponse
import org.springframework.data.domain.Page
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component
import java.time.LocalDate
import java.time.Period

@Component
object Helpers {
    fun getUserDetails() =
        SecurityContextHolder.getContext()
                .authentication
                ?.let {
                    it.principal as UserDetails
                } ?: throw IllegalStateException("UserDetails not found")

    fun calculateAge(birthdate: LocalDate) =
        Period.between(birthdate, LocalDate.now())
                .years

    fun <T : Any> getPagedResponse(data: Page<T>) =
        PagedResponse(
            data = data.content,
            pageNumber = data.number + 1,
            pageSize = data.size,
            totalItems = data.totalElements,
            totalPages = data.totalPages,
            hasNext = data.hasNext(),
            hasPrevious = data.hasPrevious()
        )
}
