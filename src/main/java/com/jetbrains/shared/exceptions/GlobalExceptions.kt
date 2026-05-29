package com.jetbrains.shared.exceptions

import com.jetbrains.shared.dtos.ApiResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.authorization.AuthorizationDeniedException
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptions {
    @ExceptionHandler(Exception::class)
    fun handleException(e: Exception): ResponseEntity<ApiResponse<List<String>>> =
        ResponseEntity(
            ApiResponse(listOf(e.message ?: "unknown error")),
            HttpStatus.BAD_REQUEST
        )

    @ExceptionHandler(CustomExceptionHandler::class)
    fun handleCustomException(e: CustomExceptionHandler): ResponseEntity<ApiResponse<List<String>>> =
        ResponseEntity(
            ApiResponse(listOf(e.message ?: "unknown error")),
            e.code ?: HttpStatus.BAD_REQUEST
        )

    @ExceptionHandler(UsernameNotFoundException::class)
    fun handleUsernameNotFoundException(e: UsernameNotFoundException): ResponseEntity<ApiResponse<List<String>>> =
        ResponseEntity(ApiResponse(listOf(e.message ?: "unknown error")), HttpStatus.NOT_FOUND)

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleMethodArgumentNotValidException(e: MethodArgumentNotValidException): ResponseEntity<ApiResponse<List<String>>> =
        ResponseEntity(
            ApiResponse(listOf(e.message)),
            HttpStatus.BAD_REQUEST
        )

    @ExceptionHandler(IllegalArgumentException::class)
    fun handleIllegalArgumentException(e: IllegalArgumentException): ResponseEntity<ApiResponse<List<String>>> =
        ResponseEntity(
            ApiResponse(listOf(e.message ?: "unknown error")),
            HttpStatus.BAD_REQUEST
        )

    @ExceptionHandler(NullPointerException::class)
    fun handleNullPointerException(e: NullPointerException): ResponseEntity<ApiResponse<List<String>>> =
        ResponseEntity(
            ApiResponse(listOf(e.message ?: "unknown error")),
            HttpStatus.BAD_REQUEST
        )

    @ExceptionHandler(InvalidIdException::class)
    fun handleInvalidIdException(e: InvalidIdException): ResponseEntity<ApiResponse<List<String>>> =
        ResponseEntity(
            ApiResponse(listOf(e.message)),
            HttpStatus.BAD_REQUEST
        )

    @ExceptionHandler(AuthorizationDeniedException::class)
    fun handleAuthorizationDeniedException(
        e: AuthorizationDeniedException
    ): ResponseEntity<ApiResponse<List<String>>> = ResponseEntity(
        ApiResponse(
            listOf("${e.message}, You are not authorized to perform this action")
        ),
        HttpStatus.FORBIDDEN
    )
}
