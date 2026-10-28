package com.jetbrains.gymusserverjava.memberships.repositories;

import com.jetbrains.gymusserverjava.memberships.dtos.responses.RecentMemberChecksInDto;
import com.jetbrains.gymusserverjava.memberships.entities.MemberAttendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface MemberAttendanceRepository extends JpaRepository<MemberAttendance, Integer> {

    @Query(
            value = """
                    select ma.checkDate = current_date()
                                        from MemberAttendance ma
                                        where ma.member.id = :memberId
                                        order by ma.checkDate desc
                                        limit 1
                    """
    )
    boolean isMemberChecked(@Param(value = "memberId") int memberId);

    List<MemberAttendance> findAllByMemberId(int memberId);

    @Query(
            value = """
                    select m.id,
                           m.firstName,
                           m.lastName,
                           md.personalPhoto,
                           ms.membershipType.name as membershipType,
                           ms.endDate > current_date as status,
                           ma.createdAt as checkedInAt
                    from Member m
                             join Membership ms ON m.id = ms.member.id
                             join MemberDocument md on m.id = md.member.id
                             join MemberAttendance ma on m.id = ma.member.id
                    order by ma.createdAt desc
                    limit 2
                    """
    )
    Set<RecentMemberChecksInDto> getRecentChecksInMembers();

}
