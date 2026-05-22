package com.jetbrains.gymusserverjava.sessions.repositories;

import com.jetbrains.gymusserverjava.sessions.dtos.responses.SessionResponseDto;
import com.jetbrains.gymusserverjava.sessions.entities.Session;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionRepository extends JpaRepository<Session, Integer> {

    @Query(
            value = """
                    select s.id, s.fullName, st.name as sessionTypeName, s.createdAt, s.updatedAt
                    from Session s
                    join SessionType st on s.sessionType.id = st.id
                    """
    )
    Page<SessionResponseDto> findAllSessions(Pageable pageable);

    @Query(
            value = """
                    select case when sum(s.sessionType.price) is null then 0
                                else sum(s.sessionType.price)
                                end
                    from Session s
                    """
    )
    double getTotalRevenue();

    @Query(
            value = """
                    select case
                                when sum(s.sessionType.price) is null then 0
                                else sum(s.sessionType.price)
                           end
                    from Session s
                    where month(s.createdAt) = month(current_date)
                    and year(s.createdAt) = year(current_date)
                    """
    )
    double getMonthlyRevenue();

}
