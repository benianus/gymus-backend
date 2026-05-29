package com.jetbrains.shared.exceptions

import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode

class CustomExceptionHandler(
    override val message: String,
    @JvmField var code: HttpStatusCode
) : RuntimeException(message) {
    companion object {
        @JvmStatic
        fun resourceNotFound(message: String): CustomExceptionHandler {
            return CustomExceptionHandler(message, HttpStatus.NOT_FOUND)
        }

        fun badRequest(message: String): CustomExceptionHandler {
            return CustomExceptionHandler(message, HttpStatus.BAD_REQUEST)
        }

        fun invalidToken(message: String): CustomExceptionHandler {
            return CustomExceptionHandler(message, HttpStatus.UNAUTHORIZED)
        }

        fun badCredentials(message: String): CustomExceptionHandler {
            return CustomExceptionHandler(message, HttpStatus.NOT_FOUND)
        }

        fun forbidden(message: String): CustomExceptionHandler {
            return CustomExceptionHandler(message, HttpStatus.FORBIDDEN)
        }

        @JvmStatic
        fun ageExceed(message: String): CustomExceptionHandler {
            return CustomExceptionHandler(message, HttpStatus.FORBIDDEN)
        }

        fun parentalAuthorizationNotIncluded(message: String): CustomExceptionHandler {
            return CustomExceptionHandler(message, HttpStatus.NOT_FOUND)
        }

        @JvmStatic
        fun attendanceAlreadyChecked(message: String): CustomExceptionHandler {
            return CustomExceptionHandler(message, HttpStatus.CONFLICT)
        }

        @JvmStatic
        fun membershipAlreadyActive(message: String): CustomExceptionHandler {
            return CustomExceptionHandler(message, HttpStatus.CONFLICT)
        }
    }
}