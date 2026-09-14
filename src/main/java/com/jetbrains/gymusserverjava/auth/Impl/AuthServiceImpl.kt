package com.jetbrains.gymusserverjava.auth.Impl

import com.jetbrains.gymusserverjava.auth.AuthService
import com.jetbrains.gymusserverjava.auth.dtos.requests.LoginRequestDto
import com.jetbrains.gymusserverjava.auth.dtos.requests.LogoutRequestDto
import com.jetbrains.gymusserverjava.auth.dtos.requests.RefreshTokenRequestDto
import com.jetbrains.gymusserverjava.auth.dtos.requests.RegisterRequestDto
import com.jetbrains.gymusserverjava.auth.dtos.responses.AuthResponseDto
import com.jetbrains.gymusserverjava.auth.dtos.responses.RefreshTokenResponseDto
import com.jetbrains.gymusserverjava.auth.entities.RefreshToken
import com.jetbrains.gymusserverjava.auth.entities.User
import com.jetbrains.gymusserverjava.auth.repositories.RefreshTokenRepository
import com.jetbrains.gymusserverjava.auth.repositories.UserRepository
import com.jetbrains.shared.exceptions.CustomExceptionHandler
import com.jetbrains.shared.security.JwtHelper
import com.jetbrains.shared.security.JwtProperties
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime
import kotlin.jvm.optionals.getOrElse

@Service
class AuthServiceImpl(
    private val jwtHelper: JwtHelper,
    private val userDetailsService: UserDetailsService,
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val authenticationManager: AuthenticationManager,
    private val refreshTokenRepository: RefreshTokenRepository,
    private val jwtProperties: JwtProperties
) : AuthService {
    override fun login(loginRequestDto: LoginRequestDto): AuthResponseDto {
        val authentication = authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(
                loginRequestDto.username,
                loginRequestDto.password
            )
        )

        val user = userRepository.findByUsername(loginRequestDto.username).orElseThrow {
            throw CustomExceptionHandler.resourceNotFound("User not found")
        }

        val role = user.authorities
            .map { it.authority }
            .firstOrNull() ?: "OWNER"

        val claims = hashMapOf<String, Any>(
            "user_id" to user.id,
            "role" to role
        )

        val accessToken = jwtHelper.generateAccessToken(claims, user as UserDetails)
        val refreshToken = jwtHelper.generateRefreshToken(claims, user as UserDetails)
        val expiresIn = jwtHelper.extractExpirationDate(accessToken).time

        // save refresh token in the database
        val newRefreshToken = refreshTokenRepository.save(
            RefreshToken().apply {
                this.refreshToken = refreshToken
                this.user = user
                revokedAt = LocalDateTime.now()
                expiresAt = LocalDateTime.now().plusDays(7)
            }
        )

        return AuthResponseDto(
            accessToken,
            refreshToken,
            user.username,
            role,
            expiresIn
        )
    }

    @Transactional
    override fun register(registerRequestDto: RegisterRequestDto): AuthResponseDto {
        val newUser = userRepository.save<User>(
            User(
                0,
                registerRequestDto.username,
                passwordEncoder.encode(registerRequestDto.password),
                "OWNER",
                LocalDateTime.now(),
                LocalDateTime.now()
            )
        )

        val role = newUser.role
        val extraClaims = hashMapOf<String, Any>(
            "user_id" to newUser.id,
            "role" to role
        )


        val accessToken = jwtHelper.generateAccessToken(extraClaims, newUser as UserDetails)
        val refreshToken = jwtHelper.generateRefreshToken(extraClaims, newUser as UserDetails)
        val expiresIn = jwtHelper.extractExpirationDate(accessToken).time
        val username = newUser.username

        // save refresh token in the database
        refreshTokenRepository.save(
            RefreshToken().apply {
                this.refreshToken = refreshToken
                user = newUser
                revokedAt = LocalDateTime.now()
                expiresAt = LocalDateTime.now().plusDays(7)
            }
        )

        return AuthResponseDto(
            accessToken,
            refreshToken,
            username,
            role,
            expiresIn
        )
    }

    override fun refreshToken(refreshTokenRequestDto: RefreshTokenRequestDto): RefreshTokenResponseDto {
        val user = userRepository.findByUsername(refreshTokenRequestDto.username).getOrElse {
            throw CustomExceptionHandler.resourceNotFound("User not found")
        }

        val isTokenValid =
            jwtHelper.isTokenValid(refreshTokenRequestDto.refreshToken, user as UserDetails)

        if (!isTokenValid) throw CustomExceptionHandler.invalidToken("invalid token")

        val role = user.authorities.map { it.authority }.firstOrNull() ?: "OWNER"

        val claims = hashMapOf<String, Any>(
            "user_id" to user.id,
            "role" to role
        )

        val accessToken = jwtHelper.generateAccessToken(claims, user as UserDetails)
        val refreshToken = jwtHelper.generateRefreshToken(claims, user as UserDetails)

        // save or update new refresh token in the database
        refreshTokenRepository.save(
            RefreshToken().apply {
                this.user = user
                this.refreshToken = refreshToken
                revokedAt = LocalDateTime.now()
                expiresAt = LocalDateTime.now().plusDays(7)
            }
        )

        return RefreshTokenResponseDto(
            accessToken = accessToken,
            refreshToken = refreshToken
        )
    }

    override fun logout(logoutRequestDto: LogoutRequestDto) {
        val user = userRepository.findByUsername(logoutRequestDto.username).orElseThrow {
            throw CustomExceptionHandler.logoutException("logout succeed")
        }

        val isTokenValid =
            jwtHelper.isTokenValid(logoutRequestDto.refreshToken, user as UserDetails)

        if (!isTokenValid) throw CustomExceptionHandler.logoutException("logout succeed")

        val refreshToken = refreshTokenRepository.findByRefreshToken(logoutRequestDto.refreshToken)
            .orElseThrow { throw CustomExceptionHandler.logoutException("logout succeed") }

        if (refreshToken.revokedAt == null ||
            refreshToken.expiresAt.isBefore(LocalDateTime.now()) ||
            refreshToken.expiresAt == null
        ) throw CustomExceptionHandler.logoutException("logout succeed")

        refreshToken.apply {
            this.refreshToken = null
            this.revokedAt = null
            this.expiresAt = null
        }

        refreshTokenRepository.save(refreshToken)
    }
}
