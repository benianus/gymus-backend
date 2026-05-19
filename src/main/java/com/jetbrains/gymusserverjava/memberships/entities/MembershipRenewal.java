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
@Table(name = "membership_renewals")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MembershipRenewal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id = 0;

    @Column(name = "renewed_date", nullable = false, columnDefinition = "date default current_date")
    private LocalDate renewalDate = LocalDate.now();

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
    @JoinColumn(name = "member_id", nullable = false)
    private Member member = null;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "membership_id", nullable = false)
    private Membership membership = null;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "created_by", nullable = false)
    private User user = null;

    public int getMembership() {
        return membership.getId();
    }

    public int getUser() {
        return user.getId();
    }

}


/**
 * create table membership_renewals
 * (
 * id            int not null primary key generated always as identity,
 * membership_id int not null references memberships (id),
 * created_by    int not null references users (id),
 * created_at    timestamp(2) default current_timestamp(2),
 * updated_at    timestamp(2) default current_timestamp(2)
 * );
 */