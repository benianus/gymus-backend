package com.jetbrains.gymusserverjava.memberships.repositories;

import com.jetbrains.gymusserverjava.memberships.entities.MembershipRenewal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MembershipRenewalRepository extends JpaRepository<MembershipRenewal, Integer> {

    List<MembershipRenewal> findAllByMemberId(int memberId);

}
