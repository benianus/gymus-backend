package com.jetbrains.gymusserverjava.store.repositories;

import com.jetbrains.gymusserverjava.store.entities.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Integer> {

    @Query(
            value = """
                    select case
                                when sum(s.totalPrice) is null then 0
                                else sum(s.totalPrice)
                                end
                    from Sale s
                    """
    )
    double getTotalRevenue();

    @Query(
            value = """
                    select case
                                when sum(s.totalPrice) is null then 0
                                else sum(s.totalPrice)
                           end
                    from Sale s
                    where month(s.createdAt) = month(current_date)
                    and year(s.createdAt) = year(current_date)
                    """
    )
    double getMonthlyRevenue();

    @Query(
            """
            select count(s.id) from Sale s
            """
    )
    int getTotalSales();

    @Query(
            """
            select count(s.id) from Sale s where month(s.createdAt) = month(current_date)
                        and year(s.createdAt) = year(current_date)
            """
    )
    int getMonthlySales();

}
