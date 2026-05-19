package com.jetbrains.shared.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtHelper {

    private final JwtProperties jwtProperties;

    public JwtHelper(JwtProperties jwtProperties) {
        this.jwtProperties = jwtProperties;
    }

    public SecretKey setSecretKey() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtProperties.getKey()));
    }

    public String generateToken(
            Map<String, Object> claims,
            UserDetails userDetails,
            Long expiration
    ) {
        return Jwts.builder()
                   .subject(userDetails.getUsername())
                   .issuedAt(new Date(System.currentTimeMillis()))
                   .expiration(new Date(System.currentTimeMillis() + expiration))
                   .claims(claims)
                   .signWith(setSecretKey())
                   .compact();
    }

    public String generateAccessToken(Map<String, Object> claims, UserDetails userDetails) {
        return generateToken(claims, userDetails, jwtProperties.getAccessTokenExpiration());
    }

    public String generateRefreshToken(Map<String, Object> claims, UserDetails userDetails) {
        return generateToken(claims, userDetails, jwtProperties.getRefreshTokenExpiration());
    }

    public Claims extractAllClaims(String token) {
        return Jwts
                .parser()
                .verifyWith(setSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        var claim = extractAllClaims(token);
        return claimsResolver.apply(claim);
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpirationDate(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        var username = extractUsername(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        var expirationDate = extractExpirationDate(token);
        return expirationDate.before(new Date());
    }

}
