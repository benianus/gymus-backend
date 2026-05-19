package com.jetbrains.gymusserverjava.memberships.entities;

import com.jetbrains.gymusserverjava.auth.entities.User;
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
@Table(name = "members_cards")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MemberCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id = 0;

    @Column(name = "join_date", nullable = false, columnDefinition = "date default current_date")
    private LocalDate joinDate = LocalDate.now();

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

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false, unique = true)
    private Member member = null;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "membership_id", nullable = false, unique = true)
    private Membership membership = null;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "created_by", nullable = false)
    private User user = null;

    public int getMember() {
        return member.getId();
    }

    public int getMembership() {
        return membership.getId();
    }

    public int getUser() {
        return user.getId();
    }

}


/**
 * create table members_cards
 * (
 * id            int not null primary key generated always as identity,
 * join_date     date not null default current_date,
 * member_id     int not null references members (id) unique,
 * membership_id int not null references memberships (id) unique,
 * created_by    int not null references users (id),
 * created_at    timestamp(2) default current_timestamp(2),
 * updated_at    timestamp(2) default current_timestamp(2)
 * );
 */