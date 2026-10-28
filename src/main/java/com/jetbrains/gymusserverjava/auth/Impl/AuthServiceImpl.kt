@file:OptIn(ExperimentalUuidApi::class)

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
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

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

        val user = userRepository.findByUsername(loginRequestDto.username).getOrElse {
            throw CustomExceptionHandler.resourceNotFound("User not found")
        }

        val role = user.authorities
            .map { it.authority }
            .firstOrNull() ?: "OWNER"

        val claims = generateClaims(user, role)

        val accessToken = jwtHelper.generateAccessToken(claims, user as UserDetails)
        val refreshToken = Uuid.generateV7().toString()
        val expiresIn = jwtHelper.extractExpirationDate(accessToken).time

        // save refresh token in the database
        refreshTokenRepository.save(RefreshToken().apply {
            token = refreshToken
            createdAt = LocalDateTime.now()
            updatedAt = LocalDateTime.now()
            this.user = user
            revokedAt = null
            expiresAt = LocalDateTime.now().plusDays(7)
        })

        return AuthResponseDto(
            accessToken = accessToken,
            refreshToken = refreshToken,
            username = user.username,
            role = role,
            expiresIn = expiresIn
        )
    }

    @Transactional
    override fun register(registerRequestDto: RegisterRequestDto): AuthResponseDto {
        val newUser = userRepository.save(
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
        val claims = generateClaims(newUser, role)

        val accessToken = jwtHelper.generateAccessToken(claims, newUser as UserDetails)
        val refreshToken = Uuid.generateV7().toString()
        val expiresIn = jwtHelper.extractExpirationDate(accessToken).time
        val username = newUser.username

        // save refresh token in the database
        refreshTokenRepository.save(
            RefreshToken().apply {
                token = refreshToken
                createdAt = LocalDateTime.now()
                updatedAt = LocalDateTime.now()
                this.user = newUser
                revokedAt = null
                expiresAt = LocalDateTime.now().plusDays(jwtProperties.refreshTokenExpiration)
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

        val refreshToken =
            refreshTokenRepository.findByToken(refreshTokenRequestDto.refreshToken)
                .getOrElse { throw CustomExceptionHandler.resourceNotFound("Token not found") }

        // validate is token valid
        if (refreshToken.revokedAt != null) {
            throw CustomExceptionHandler.resourceNotFound("refresh token already revoked, try to login again!")
        }

        if (refreshToken.expiresAt.isBefore(LocalDateTime.now())) {
            throw CustomExceptionHandler.resourceNotFound("refresh token expired, try to login again!")
        }

        // generate access token
        val role = user.authorities.map { it.authority }.firstOrNull() ?: "OWNER"
        val claims = generateClaims(user, role)
        val accessToken = jwtHelper.generateAccessToken(claims, user as UserDetails)

        return RefreshTokenResponseDto(accessToken = accessToken, refreshToken = refreshToken.token)
    }

    override fun logout(logoutRequestDto: LogoutRequestDto) {
        userRepository.findByUsername(logoutRequestDto.username).getOrElse {
            throw CustomExceptionHandler.logoutException("logout succeed")
        }

        val refreshToken = refreshTokenRepository.findByToken(logoutRequestDto.refreshToken)
            .getOrElse { throw CustomExceptionHandler.logoutException("logout succeed") }

        if (refreshToken.revokedAt != null) throw CustomExceptionHandler.logoutException("logout succeed")

        refreshToken.apply {
            revokedAt = LocalDateTime.now()
        }

        refreshTokenRepository.save(refreshToken)
    }

    private fun generateClaims(
        user: User,
        role: String
    ): HashMap<String, Any> = hashMapOf(
        "user_id" to user.id,
        "role" to role
    )

}
