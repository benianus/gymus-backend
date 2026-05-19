package com.jetbrains.gymusserverjava.memberships.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Entity
@Table(name = "membership_types")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MembershipType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id = 0;

    @Column(name = "name", nullable = false)
    private String name = "";

    @Column(name = "price", nullable = false, columnDefinition = "numeric check (price > 0)")
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
 * create table membership_type
 * (
 * id         int          not null primary key generated always as identity,
 * name       varchar(255) not null,
 * price      numeric      not null check ( price > 0 ),
 * created_at timestamp(2) default current_timestamp(2),
 * updated_at timestamp(2) default current_timestamp(2)
 * );
 */