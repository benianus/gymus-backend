package com.jetbrains.gymusserverjava.memberships.repositories;

import com.jetbrains.gymusserverjava.memberships.entities.MemberDocument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberDocumentRepository extends JpaRepository<MemberDocument, Integer> {

    Optional<MemberDocument> findOneByMemberId(int memberId);

}
