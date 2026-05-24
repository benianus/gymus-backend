package com.jetbrains.gymusserverjava.memberships.entities;

import com.jetbrains.gymusserverjava.auth.entities.User;
import com.jetbrains.shared.utils.Helpers;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "members")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id = 0;

    @Column(name = "first_name", nullable = false, length = 100)
    private String firstName = "";

    @Column(name = "last_name", nullable = false, length = 100)
    private String lastName = "";

    @Column(name = "email", nullable = false, unique = true)
    private String email = "";

    @Column(name = "phone_number", nullable = false, length = 25, unique = true)
    private String phoneNumber = "";

    @Column(name = "address", nullable = false, columnDefinition = "text")
    private String address = "";

    @Column(name = "birthdate", nullable = false, columnDefinition = "date")
    private LocalDate birthdate = LocalDate.now();

    @CreationTimestamp
    @Column(name = "created_at", columnDefinition = "timestamp(2) default current_timestamp(2)")
    private LocalDateTime createdAt = LocalDateTime.now();

    @UpdateTimestamp
    @LastModifiedDate
    @Column(name = "updated_at", columnDefinition = "timestamp(2) default current_timestamp(2)")
    private LocalDateTime updatedAt = LocalDateTime.now();

    /**
     * relationship
     */

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "created_by", nullable = false)
    private User user = null;

    public int getAge() {
        return Helpers.INSTANCE.calculateAge(birthdate);
    }

    public int getUser() {
        return user.getId();
    }

}

/*
  create table persons
  (
  id          int          not null primary key generated always AS IDENTITY,
  first_name  varchar(100) not null,
  last_name   varchar(100) not null,
  birthdate   date         not null,
  email       varchar(255) not null unique,
  phoneNumber varchar(25)  not null unique,
  address     text         not null,
  created_by  int          not null references users (id),
  created_at  timestamp(2) default current_timestamp(2),
  updated_at  timestamp(2) default current_timestamp(2)
  );
 */