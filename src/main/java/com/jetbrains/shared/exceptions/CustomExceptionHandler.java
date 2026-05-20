package com.jetbrains.shared.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

public class CustomExceptionHandler extends RuntimeException {

    public String message;
    public HttpStatusCode code;

    public CustomExceptionHandler(String message, HttpStatusCode code) {
        super(message);
        this.message = message;
        this.code = code;
    }

    public static CustomExceptionHandler resourceNotFound(String message) {
        return new CustomExceptionHandler(message, HttpStatus.NOT_FOUND);
    }

    public static CustomExceptionHandler badRequest(String message) {
        return new CustomExceptionHandler(message, HttpStatus.BAD_REQUEST);
    }

    public static CustomExceptionHandler invalidToken(String message) {
        return new CustomExceptionHandler(message, HttpStatus.UNAUTHORIZED);
    }

    public static CustomExceptionHandler badCredentials(String message) {
        return new CustomExceptionHandler(message, HttpStatus.NOT_FOUND);
    }

    public static CustomExceptionHandler forbidden(String message) {
        return new CustomExceptionHandler(message, HttpStatus.FORBIDDEN);
    }

    public static CustomExceptionHandler ageExceed(String message) {
        return new CustomExceptionHandler(message, HttpStatus.FORBIDDEN);
    }

    public static CustomExceptionHandler parentalAuthorizationNotIncluded(String message) {
        return new CustomExceptionHandler(message, HttpStatus.NOT_FOUND);
    }

    public static CustomExceptionHandler attendanceAlreadyChecked(String message) {
        return new CustomExceptionHandler(message, HttpStatus.CONFLICT);
    }

    public static CustomExceptionHandler membershipAlreadyActive(String message) {
        return new CustomExceptionHandler(message, HttpStatus.CONFLICT);
    }

}