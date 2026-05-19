package com.jetbrains.gymusserverjava.memberships.entities;

import com.jetbrains.gymusserverjava.auth.entities.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.jspecify.annotations.Nullable;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Entity
@Table(name = "member_documents")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MemberDocument {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id = 0;

    @Column(name = "medical_certificate", nullable = false)
    private String medicalCertificate = "";

    @Column(name = "birth_certificate", nullable = false)
    private String birthCertificate = "";

    @Column(name = "personal_photo", nullable = false)
    private String personalPhoto = "";

    @Column(name = "parental_authorization", columnDefinition = "varchar default null")
    @Nullable
    private String parentalAuthorization = null;

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

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "member_id", nullable = false, unique = true)
    private Member member = null;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "added_by", nullable = false)
    private User user = null;

    public int getMember() {
        return member.getId();
    }

    public int getUser() {
        return user.getId();
    }

}

/***
 * create table member_documents
 * (
 *     id                     int  not null primary key generated always as identity,
 *     birth_certificate      text not null,
 *     medical_certificate    text not null,
 *     personal_photo         text not null,
 *     parental_authorization text         default null,
 *     member_id              int  not null unique references members (id),
 *     added_by               int  not null references users (id),
 *     created_at             timestamp(2) default current_timestamp(2),
 *     updated_at             timestamp(2) default current_timestamp(2)
 * );
 */