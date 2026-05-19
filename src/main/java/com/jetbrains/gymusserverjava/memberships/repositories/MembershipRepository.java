package com.jetbrains.gymusserverjava.memberships.repositories;

import com.jetbrains.gymusserverjava.memberships.entities.Membership;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MembershipRepository extends JpaRepository<Membership, Integer> {

    //    @Query(
    //            value = """
    //                    select ms from Membership ms where ms.member.id = :memberId
    //                    """
    //    )
    Optional<Membership> findByMemberId(int memberId);

}
