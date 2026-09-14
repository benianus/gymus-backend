package com.jetbrains.gymusserverjava.auth;

import com.jetbrains.gymusserverjava.auth.dtos.requests.LoginRequestDto;
import com.jetbrains.gymusserverjava.auth.dtos.requests.LogoutRequestDto;
import com.jetbrains.gymusserverjava.auth.dtos.requests.RefreshTokenRequestDto;
import com.jetbrains.gymusserverjava.auth.dtos.requests.RegisterRequestDto;
import com.jetbrains.gymusserverjava.auth.dtos.responses.AuthResponseDto;
import com.jetbrains.gymusserverjava.auth.dtos.responses.RefreshTokenResponseDto;

public interface AuthService {

    AuthResponseDto login(LoginRequestDto loginRequestDto);

    AuthResponseDto register(RegisterRequestDto registerRequestDto);

    RefreshTokenResponseDto refreshToken(RefreshTokenRequestDto refreshTokenRequestDto);

    void logout(LogoutRequestDto logoutRequestDto);

}