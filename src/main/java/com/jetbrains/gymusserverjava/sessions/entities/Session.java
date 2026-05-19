package com.jetbrains.gymusserverjava.sessions.entities;

import com.jetbrains.gymusserverjava.auth.entities.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Entity
@Table(name = "sessions")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Session {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id = 0;

    @Column(name = "full_name", nullable = false)
    private String fullName = "";

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
    @JoinColumn(name = "session_type_id", nullable = false)
    private SessionType sessionType = null;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "created_by", nullable = false)
    private User user = null;

    public int getSessionType() {
        return sessionType.getId();
    }

    public int getUser() {
        return user.getId();
    }
}


/**
 * create table sessions
 * (
 * id              int          not null primary key generated always as identity,
 * full_name       varchar(255) not null,
 * session_type_id int          not null references session_types (id),
 * created_by      int          not null references users (id),
 * created_at      timestamp(2) default current_timestamp(2),
 * updated_at      timestamp(2) default current_timestamp(2)
 * );
 */