package com.jetbrains.gymusserverjava.auth;

import com.jetbrains.gymusserverjava.auth.dtos.requests.LoginRequestDto;
import com.jetbrains.gymusserverjava.auth.dtos.requests.RegisterRequestDto;
import com.jetbrains.gymusserverjava.auth.dtos.responses.AuthResponseDto;

public interface AuthService {
    AuthResponseDto login(LoginRequestDto loginRequestDto);

    AuthResponseDto register(RegisterRequestDto registerRequestDto);
}