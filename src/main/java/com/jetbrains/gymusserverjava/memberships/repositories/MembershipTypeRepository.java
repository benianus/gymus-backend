package com.jetbrains.gymusserverjava.memberships.repositories;

import com.jetbrains.gymusserverjava.memberships.entities.MembershipType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MembershipTypeRepository extends JpaRepository<MembershipType, Integer> {
    Optional<MembershipType> findMembershipTypeByName(String name);
}
