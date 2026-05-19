package com.jetbrains.gymusserverjava.sessions.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Entity
@Table(name = "session_types")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SessionType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id = 0;

    @Column(name = "name", nullable = false)
    private String name = "";

    @Column(name = "price", nullable = false)
    private double price = 0.0d;
    @CreationTimestamp
    @Column(name = "created_at", columnDefinition = "timestamp(2) default current_timestamp(2)")
    private LocalDateTime createdAt = LocalDateTime.now();

    @UpdateTimestamp
    @LastModifiedDate
    @Column(name = "updated_at", columnDefinition = "timestamp(2) default current_timestamp(2)")
    private LocalDateTime updatedAt = LocalDateTime.now();
}


/**
 * create table session_types
 * (
 * id         int          not null primary key generated always as identity,
 * name       varchar(100) not null,
 * price      numeric      not null,
 * created_at timestamp(2) default current_timestamp(2),
 * updated_at timestamp(2) default current_timestamp(2)
 * );
 */