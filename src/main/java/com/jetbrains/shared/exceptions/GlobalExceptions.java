package com.jetbrains.shared.exceptions;

import com.jetbrains.shared.dtos.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptions {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<List<String>>> handleException(Exception e) {
        var errors = List.of(e.getMessage());
        return new ResponseEntity<>(new ApiResponse<>(errors), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CustomExceptionHandler.class)
    public ResponseEntity<ApiResponse<List<String>>> handleCustomException(CustomExceptionHandler e) {
        var errors = List.of(e.getMessage());
        return new ResponseEntity<>(new ApiResponse<>(errors), e.code);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ApiResponse<List<String>>> handleUsernameNotFoundException(
            UsernameNotFoundException e
    ) {
        var errors = List.of(e.getMessage());
        return new ResponseEntity<>(new ApiResponse<>(errors), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<List<String>>> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException e
    ) {
        var errors = List.of(e.getMessage());
        return new ResponseEntity<>(new ApiResponse<>(errors), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiResponse<List<String>>> handleIllegalArgumentException(
            IllegalArgumentException e
    ) {
        var errors = List.of(e.getMessage());
        return new ResponseEntity<>(new ApiResponse<>(errors), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ApiResponse<List<String>>> handleNullPointerException(NullPointerException e) {
        var errors = List.of(e.getMessage());
        return new ResponseEntity<>(new ApiResponse<>(errors), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidIdException.class)
    public ResponseEntity<ApiResponse<List<String>>> handleInvalidIdException(InvalidIdException e) {
        var errors = List.of(e.getMessage());
        return new ResponseEntity<>(new ApiResponse<>(errors), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AuthorizationDeniedException.class)
    public ResponseEntity<ApiResponse<List<String>>> handleAuthorizationDeniedException(
            AuthorizationDeniedException e
    ) {
        var errors = List.of(e.getMessage() + ", You are not authorized to perform this action");
        return new ResponseEntity<>(new ApiResponse<>(errors), HttpStatus.FORBIDDEN);
    }

}
