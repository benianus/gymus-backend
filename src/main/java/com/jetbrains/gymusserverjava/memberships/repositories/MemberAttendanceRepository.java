package com.jetbrains.gymusserverjava.memberships.repositories;

import com.jetbrains.gymusserverjava.memberships.entities.MemberAttendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

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
    boolean isMemberChecked(
            @Param(value = "memberId") int memberId
    );

}
