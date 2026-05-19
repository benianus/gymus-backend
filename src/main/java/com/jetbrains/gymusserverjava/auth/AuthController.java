package com.jetbrains.gymusserverjava.auth;

import com.jetbrains.gymusserverjava.auth.dtos.requests.LoginRequestDto;
import com.jetbrains.gymusserverjava.auth.dtos.requests.RegisterRequestDto;
import com.jetbrains.gymusserverjava.auth.dtos.responses.AuthResponseDto;
import com.jetbrains.shared.dtos.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    ResponseEntity<ApiResponse<AuthResponseDto>> login(@RequestBody LoginRequestDto request) {
        var authResponse = authService.login(request);
        return new ResponseEntity<>(new ApiResponse<>(authResponse), HttpStatus.OK);
    }

    @PostMapping("/register")
    ResponseEntity<ApiResponse<AuthResponseDto>> register(@RequestBody RegisterRequestDto request) {
        var authResponse = authService.register(request);
        return new ResponseEntity<>(new ApiResponse<>(authResponse), HttpStatus.CREATED);
    }

}
