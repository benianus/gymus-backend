package com.jetbrains.gymusserverjava.store.entities;

import com.jetbrains.gymusserverjava.auth.entities.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Entity
@Table(name = "sales")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id = 0;

    @Column(name = "quantity", nullable = false)
    private int quantity = 0;

    @Column(name = "total_price", nullable = false, columnDefinition = "numeric check ( total_price > 0 )")
    private double totalPrice = 0.0d;

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
    @JoinColumn(name = "product_id", nullable = false)
    private Product product = null;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "made_by", nullable = false)
    private User user = null;

    public int getUser() {
        return user.getId();
    }
}


/**
 * create table sales
 * (
 * id          int     not null primary key generated always as identity,
 * product_id  int     not null references products (id),
 * quantity    int     not null,
 * total_price numeric not null check ( total_price > 0 ),
 * made_by     int     null default null references users (id),
 * created_at  timestamp(2) default current_timestamp(2),
 * updated_at  timestamp(2) default current_timestamp(2)
 * );
 */