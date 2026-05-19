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
@Table(name = "memberships")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Membership {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id = 0;

    @Column(name = "start_date", nullable = false, columnDefinition = "date default current_date")
    private LocalDate startDate = LocalDate.now();

    @Column(
            name = "end_date",
            nullable = false,
            columnDefinition = "date default (current_date + 30)"
    )
    private LocalDate endDate = LocalDate.now().plusDays(30);

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

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "member_id", nullable = false, unique = true)
    private Member member = null;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "membership_type_id", nullable = false)
    private MembershipType membershipType = null;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "created_by", nullable = false)
    private User user = null;

    public int getMember() {
        return member.getId();
    }

    public int getMembershipType() {
        return membershipType.getId();
    }

    public int getUser() {
        return user.getId();
    }

}


/**
 * create table memberships
 * (
 * id                 int not null primary key generated always as identity,
 * start_date         date         default current_date,
 * end_date           date         default (current_date + 30),
 * member_id          int not null unique references members (id),
 * membership_type_id int not null references membership_type (id),
 * created_by         int not null references users (id),
 * created_at         timestamp(2) default current_timestamp(2),
 * updated_at         timestamp(2) default current_timestamp(2)
 * );
 */