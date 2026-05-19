package com.jetbrains.shared.exceptions;

import org.springframework.http.HttpStatus;

public class CustomExceptionHandler extends RuntimeException {

    public String message;
    public int code;

    public CustomExceptionHandler(String message, int code) {
        super(message);
        this.message = message;
        this.code = code;
    }

    public static CustomExceptionHandler resourceNotFound(String message) {
        return new CustomExceptionHandler(message, HttpStatus.NOT_FOUND.value());
    }

    public static CustomExceptionHandler badRequest(String message) {
        return new CustomExceptionHandler(message, HttpStatus.BAD_REQUEST.value());
    }

    public static CustomExceptionHandler invalidToken(String message) {
        return new CustomExceptionHandler(message, HttpStatus.UNAUTHORIZED.value());
    }

    public static CustomExceptionHandler badCredentials(String message) {
        return new CustomExceptionHandler(message, HttpStatus.NOT_FOUND.value());
    }

    public static CustomExceptionHandler forbidden(String message) {
        return new CustomExceptionHandler(message, HttpStatus.FORBIDDEN.value());
    }

    public static CustomExceptionHandler ageExceed(String message) {
        return new CustomExceptionHandler(message, HttpStatus.FORBIDDEN.value());
    }

    public static CustomExceptionHandler parentalAuthorizationNotIncluded(String message) {
        return new CustomExceptionHandler(message, HttpStatus.NOT_FOUND.value());
    }

    public static CustomExceptionHandler attendanceAlreadyChecked(String message) {
        return new CustomExceptionHandler(message, HttpStatus.CONFLICT.value());
    }

    public static CustomExceptionHandler membershipAlreadyActive(String message) {
        return new CustomExceptionHandler(message, HttpStatus.CONFLICT.value());
    }

}