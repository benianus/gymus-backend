package com.jetbrains.gymusserverjava.reports.implementations

import com.jetbrains.gymusserverjava.memberships.repositories.MembershipRepository
import com.jetbrains.gymusserverjava.reports.services.SalesReportsService
import com.jetbrains.gymusserverjava.sessions.repositories.SessionRepository
import com.jetbrains.gymusserverjava.store.repositories.SaleRepository
import org.springframework.stereotype.Service

@Service
class SalesReportsServiceImpl(
    private val membershipRepository: MembershipRepository,
    private val saleRepository: SaleRepository,
    private val sessionRepository: SessionRepository,
) : SalesReportsService {
    override fun totalSales(): Int =
        membershipRepository.totalSales + saleRepository.totalSales + sessionRepository.totalSales

    override fun monthlySales(): Int =
        membershipRepository.monthlySales + sessionRepository.monthlySales + saleRepository.totalSales

    override fun totalStoreSales(): Int = saleRepository.totalSales

    override fun monthlyStoreSales(): Int = saleRepository.monthlySales

    override fun totalSessionsSales(): Int = sessionRepository.totalSales

    override fun monthlySessionSales(): Int = sessionRepository.monthlySales

    override fun totalMembershipsSales(): Int = membershipRepository.totalSales

    override fun monthlyMembershipsSales(): Int = membershipRepository.monthlySales

    override fun totalActiveMembershipsSales(): Int = membershipRepository.totalActiveSales()

    override fun monthlyActiveMembershipsSales(): Int = membershipRepository.monthlyActiveSales()
}