package com.jetbrains.gymusserverjava.memberships.repositories;

import com.jetbrains.gymusserverjava.memberships.dtos.responses.MemberResponseDto;
import com.jetbrains.gymusserverjava.memberships.entities.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Integer> {

    @Query(
            value = """
                    select
                           m.id,
                           m.firstName,
                           m.lastName,
                           m.email,
                           m.phoneNumber,
                           m.address,
                           m.birthdate,
                           md.personalPhoto,
                           ms.endDate,
                           m.createdAt,
                           m.updatedAt
                    from Member m
                             join MemberDocument md on m.id = md.member.id
                             join Membership ms on m.id = ms.member.id
                    """
    )
    Page<MemberResponseDto> findAllMembers(Pageable pageable);

}
