package com.jetbrains.gymusserverjava.memberships.repositories;

import com.jetbrains.gymusserverjava.memberships.entities.Membership;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MembershipRepository extends JpaRepository<Membership, Integer> {

    Optional<Membership> findByMemberId(int memberId);

    Optional<Membership> findOneByMemberId(int memberId);

    @Query(
            value = """
                    select case
                                when sum(m.membershipType.price) is null then 0
                                else sum(m.membershipType.price)
                           end
                    from Membership m
                    """
    )
    double getTotalRevenue();

    @Query(
            value = """
                    select case
                                when sum(m.membershipType.price) is null then 0
                                else sum(m.membershipType.price)
                           end
                    from Membership m
                    where month(m.createdAt) = month(current_date)
                    and year(m.createdAt) = year(current_date)
                    """
    )
    double getMonthlyRevenue();

    @Query(
            value = """
                    select case
                                 when sum(m.membershipType.price) is null then 0
                                 else sum(m.membershipType.price)
                                 end
                    from Membership m
                    where m.endDate > current_date
                    """
    )
    double getTotalActiveRevenue();

    @Query(
            value = """
                    select case
                                 when sum(m.membershipType.price) is null then 0
                                 else sum(m.membershipType.price)
                                 end
                    from Membership m
                    where m.endDate > current_date
                    and month(m.createdAt) = month(current_date)
                    and year(m.createdAt) = year(current_date)
                    """
    )
    double getMonthlyActiveRevenue();

}
