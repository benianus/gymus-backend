package com.jetbrains.gymusserverjava.auth.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "refresh_tokens")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RefreshToken {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    @Column(name = "refresh_token")
    private String refreshToken;

    @Column(name = "revoked_at", columnDefinition = "timestamp")
    private LocalDateTime revokedAt;

    @Column(name = "expires_at", columnDefinition = "timestamp")
    private LocalDateTime expiresAt;

}
