package com.jetbrains.shared.exceptions

import com.jetbrains.shared.dtos.ApiResponse
import com.jetbrains.shared.dtos.ApiResponse.Error
import com.jetbrains.shared.dtos.ErrorResponseDto
import io.jsonwebtoken.ExpiredJwtException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authorization.AuthorizationDeniedException
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptions {
    @ExceptionHandler(Exception::class)
    fun handleException(e: Exception): ResponseEntity<ApiResponse<Unit, ErrorResponseDto>> =
        ResponseEntity(
            Error(ErrorResponseDto(e.message ?: "unknown error")),
            HttpStatus.BAD_REQUEST
        )

    @ExceptionHandler(CustomExceptionHandler::class)
    fun handleCustomException(
        e: CustomExceptionHandler
    ): ResponseEntity<ApiResponse<Unit, ErrorResponseDto>> =
        ResponseEntity(
            Error(ErrorResponseDto(e.message)),
            e.code
        )

    @ExceptionHandler(UsernameNotFoundException::class)
    fun handleUsernameNotFoundException(
        e: UsernameNotFoundException
    ): ResponseEntity<ApiResponse<Unit, ErrorResponseDto>> =
        ResponseEntity(
            Error(ErrorResponseDto(e.message ?: "unknown error")),
            HttpStatus.NOT_FOUND
        )

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleMethodArgumentNotValidException(
        e: MethodArgumentNotValidException
    ): ResponseEntity<ApiResponse<Unit, ErrorResponseDto>> =
        ResponseEntity(
            Error(ErrorResponseDto(e.message)),
            HttpStatus.BAD_REQUEST
        )

    @ExceptionHandler(IllegalArgumentException::class)
    fun handleIllegalArgumentException(
        e: IllegalArgumentException
    ): ResponseEntity<ApiResponse<Unit, ErrorResponseDto>> =
        ResponseEntity(
            Error(ErrorResponseDto(e.message ?: "unknown error")),
            HttpStatus.BAD_REQUEST
        )

    @ExceptionHandler(NullPointerException::class)
    fun handleNullPointerException(
        e: NullPointerException
    ): ResponseEntity<ApiResponse<Unit, ErrorResponseDto>> =
        ResponseEntity(
            Error(ErrorResponseDto(e.message ?: "unknown error")),
            HttpStatus.BAD_REQUEST
        )

    @ExceptionHandler(InvalidIdException::class)
    fun handleInvalidIdException(
        e: InvalidIdException
    ): ResponseEntity<ApiResponse<Unit, ErrorResponseDto>> =
        ResponseEntity(
            Error(ErrorResponseDto(e.message)),
            HttpStatus.BAD_REQUEST
        )

    @ExceptionHandler(AuthorizationDeniedException::class)
    fun handleAuthorizationDeniedException(
        e: AuthorizationDeniedException
    ): ResponseEntity<ApiResponse<Unit, ErrorResponseDto>> =
        ResponseEntity(
            Error(ErrorResponseDto("${e.message}, You are not authorized to perform this action")),
            HttpStatus.FORBIDDEN
        )

    @ExceptionHandler(BadCredentialsException::class)
    fun handlerBadCredentialsException(
        e: BadCredentialsException
    ): ResponseEntity<ApiResponse<Unit, ErrorResponseDto>> =
        ResponseEntity(
            Error(ErrorResponseDto("${e.message} or invalid!")),
            HttpStatus.BAD_REQUEST
        )

    @ExceptionHandler(ExpiredJwtException::class)
    fun handleExpiredJwtException(
        e: ExpiredJwtException
    ): ResponseEntity<ApiResponse<Unit, ErrorResponseDto>> {
        return ResponseEntity(
            Error(ErrorResponseDto("${e.message}")),
            HttpStatus.FORBIDDEN
        )
    }
}
