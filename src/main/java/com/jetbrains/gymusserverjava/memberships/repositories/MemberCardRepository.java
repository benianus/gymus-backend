package com.jetbrains.gymusserverjava.memberships.repositories;

import com.jetbrains.gymusserverjava.memberships.dtos.responses.MemberCardResponseDto;
import com.jetbrains.gymusserverjava.memberships.entities.MemberCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberCardRepository extends JpaRepository<MemberCard, Integer> {

    @Query(
            value = """
                    select mc.id as memberCardId,
                           m.id as memberId,
                           m.firstName,
                           m.lastName,
                           m.birthdate,
                           mc.joinDate,
                           ms.startDate,
                           ms.endDate,
                           md.personalPhoto,
                           (select ma.checkDate
                            from MemberAttendance ma
                            where ma.member.id = :memberId
                            order by ma.checkDate desc
                            limit 1) as checkDate,
                            (select mr.renewalDate
                            from MembershipRenewal mr
                            where mr.member.id = :memberId
                            order by mr.renewalDate desc
                            limit 1) as lastRenewal
                    from Member m
                    join MemberCard mc on mc.member.id = m.id
                    join Membership ms on ms.member.id = m.id
                    join MemberDocument md on md.member.id = m.id
                    where m.id = :memberId
                    """
    )
    Optional<MemberCardResponseDto> findMemberCard(@Param(value = "memberId") int memberId);

    Optional<MemberCard> findOneByMemberId(int memberId);

}