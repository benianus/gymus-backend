package com.jetbrains.shared.security

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component
import java.util.*
import javax.crypto.SecretKey

@Component
class JwtHelper(private val jwtProperties: JwtProperties) {
    private val setSecretKey: SecretKey =
        Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtProperties.key))

    fun generateToken(
        claims: Map<String, Any>,
        userDetails: UserDetails,
        expiration: Long
    ): String =
        Jwts.builder()
            .subject(userDetails.username)
            .issuedAt(Date(System.currentTimeMillis()))
            .expiration(Date(System.currentTimeMillis() + expiration))
            .claims(claims)
            .signWith(setSecretKey)
            .compact()

    fun generateAccessToken(
        claims: Map<String, Any>,
        userDetails: UserDetails
    ): String = generateToken(claims, userDetails, jwtProperties.accessTokenExpiration)

    fun generateRefreshToken(
        claims: Map<String, Any>,
        userDetails: UserDetails
    ): String = generateToken(claims, userDetails, jwtProperties.refreshTokenExpiration)

    fun extractAllClaims(token: String): Claims =
        Jwts.parser()
            .verifyWith(setSecretKey)
            .build()
            .parseSignedClaims(token)
            .getPayload()

    fun <T> extractClaim(token: String, claimsResolver: (Claims) -> T): T {
        val claim = extractAllClaims(token)
        return claimsResolver(claim)
    }

    fun extractUsername(token: String): String = extractClaim(token) { it.subject }

    fun extractExpirationDate(token: String): Date = extractClaim(token) { it.expiration }

    fun isTokenValid(token: String, userDetails: UserDetails) =
        extractUsername(token) == userDetails.username && !isTokenExpired(token)

    private fun isTokenExpired(token: String) =
        extractExpirationDate(token).before(Date())
}
