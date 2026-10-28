package com.jetbrains.gymusserverjava.auth.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Entity
@Table(name = "refresh_tokens")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RefreshToken {

    @Id @Column(name = "token")
    private String token;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    @Column(name = "revoked_at", columnDefinition = "timestamp")
    private LocalDateTime revokedAt;

    @Column(name = "expires_at", columnDefinition = "timestamp")
    private LocalDateTime expiresAt;

    @CreationTimestamp
    @Column(name = "created_at", columnDefinition = "timestamp default current_timestamp(2)")
    private LocalDateTime createdAt = LocalDateTime.now();

    @UpdateTimestamp
    @LastModifiedDate
    @Column(name = "updated_at", columnDefinition = "timestamp default current_timestamp(2)")
    private LocalDateTime updatedAt = LocalDateTime.now();

}
