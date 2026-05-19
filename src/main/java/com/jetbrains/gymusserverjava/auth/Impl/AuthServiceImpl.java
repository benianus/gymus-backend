package com.jetbrains.gymusserverjava.auth.Impl;

import com.jetbrains.gymusserverjava.auth.AuthService;
import com.jetbrains.gymusserverjava.auth.dtos.requests.LoginRequestDto;
import com.jetbrains.gymusserverjava.auth.dtos.requests.RegisterRequestDto;
import com.jetbrains.gymusserverjava.auth.dtos.responses.AuthResponseDto;
import com.jetbrains.gymusserverjava.auth.entities.User;
import com.jetbrains.gymusserverjava.auth.repositories.UserRepository;
import com.jetbrains.shared.exceptions.CustomExceptionHandler;
import com.jetbrains.shared.security.JwtHelper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashMap;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtHelper jwtHelper;

    public AuthServiceImpl(
            JwtHelper jwtHelper,
            UserDetailsService userDetailsService,
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            AuthenticationManager authenticationManager
    ) {
        this.jwtHelper = jwtHelper;
        this.userDetailsService = userDetailsService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public AuthResponseDto login(LoginRequestDto loginRequestDto) {
        var authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequestDto.username(),
                        loginRequestDto.password()
                ));

        var userDetails = (UserDetails) authentication.getPrincipal();

        var username = userDetails != null ? userDetails.getUsername() : null;
        var role = userDetails != null
                   ? userDetails.getAuthorities()
                                .stream()
                                .map(GrantedAuthority::getAuthority)
                                .findFirst()
                                .orElseThrow(() -> CustomExceptionHandler.resourceNotFound(""))
                   : null;

        var extraClaims = new HashMap<String, Object>();
        extraClaims.put("role", role);

        var accessToken = jwtHelper.generateAccessToken(extraClaims, userDetails);
        var refreshToken = jwtHelper.generateRefreshToken(extraClaims, userDetails);
        var expiresIn = jwtHelper.extractExpirationDate(accessToken).getTime();

        // TODO: save refresh token in the database later

        return new AuthResponseDto(
                accessToken,
                refreshToken,
                username,
                role,
                expiresIn
        );
    }

    @Override
    @Transactional
    public AuthResponseDto register(RegisterRequestDto registerRequestDto) {
        var newUser = userRepository.save(
                new User(
                        0,
                        registerRequestDto.getUsername(),
                        passwordEncoder.encode(registerRequestDto.getPassword()),
                        "OWNER",
                        LocalDateTime.now(),
                        LocalDateTime.now()
                ));

        var role = newUser.getRole();
        var extraClaims = new HashMap<String, Object>() {
            {
                put("role", role);
            }
        };

        var accessToken = jwtHelper.generateAccessToken(extraClaims, newUser);
        var refreshToken = jwtHelper.generateRefreshToken(extraClaims, newUser);
        var expiresIn = jwtHelper.extractExpirationDate(accessToken).getTime();
        var username = newUser.getUsername();

        // TODO: save refresh token in the database later

        return new AuthResponseDto(
                accessToken,
                refreshToken,
                username,
                role,
                expiresIn
        );
    }

}
