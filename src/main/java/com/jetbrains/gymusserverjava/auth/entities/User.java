package com.jetbrains.gymusserverjava.auth.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.jspecify.annotations.NonNull;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id = 0;

    @Column(name = "username", nullable = false, unique = true)
    private String username = "";

    @Column(name = "password", nullable = false)
    private String password = "";

    @Column(name = "role", nullable = false, columnDefinition = "varchar(25) default 'OWNER'")
    private String role = "OWNER";

    @CreationTimestamp
    @Column(name = "created_at", columnDefinition = "timestamp(2) default current_timestamp(2)")
    private LocalDateTime createdAt = LocalDateTime.now();

    @UpdateTimestamp
    @LastModifiedDate
    @Column(name = "updated_at", columnDefinition = "timestamp(2) default current_timestamp(2)")
    private LocalDateTime updatedAt = LocalDateTime.now();

    @Override
    public @NonNull Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

}

/**
 * create table users
 * (
 * id         int          not null primary key generated always as identity,
 * username   varchar(255) not null unique,
 * password   varchar(255) not null,
 * role       varchar(50)  not null default 'OWNER',
 * created_at timestamp(2)          default current_timestamp(2),
 * updated_at timestamp(2)          default current_timestamp(2)
 * );
 */