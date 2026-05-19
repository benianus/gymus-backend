package com.jetbrains.gymusserverjava.store.entities;

import com.jetbrains.gymusserverjava.auth.entities.User;
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
@Table(name = "products")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id = 0;

    @Column(name = "product_name", nullable = false)
    private String productName = "";

    @Column(name = "product_description", nullable = false)
    private String productDescription = "";

    @Column(name = "product_image", nullable = false)
    private String productImage = "";

    @Column(name = "quantity", nullable = false)
    private int quantity = 0;

    @Column(name = "price", nullable = false, columnDefinition = "numeric check ( price > 0 )")
    private double price = 0.0d;

    @CreationTimestamp
    @Column(name = "created_at", columnDefinition = "timestamp(2) default current_timestamp(2)")
    private LocalDateTime createdAt = LocalDateTime.now();

    @UpdateTimestamp
    @LastModifiedDate
    @Column(name = "updated_at", columnDefinition = "timestamp(2) default current_timestamp(2)")
    private LocalDateTime updatedAt = LocalDateTime.now();

    /**
     * relationships
     */

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "added_by", nullable = false)
    private User user = null;

    public int getUser() {
        return user.getId();
    }

}

/**
 * create table products
 * (
 * id           int          not null primary key generated always as identity,
 * product_name varchar(255) not null,
 * product_description text not null,
 * product_image text not null,
 * quantity     int          not null,
 * price        numeric      not null check (price > 0),
 * added_by     int          not null references users (id),
 * created_at   timestamp(2) default current_timestamp(2),
 * updated_at   timestamp(2) default current_timestamp(2)
 * );
 */