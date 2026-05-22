package com.jetbrains.gymusserverjava.reports.implementations

import com.jetbrains.gymusserverjava.memberships.repositories.MembershipRepository
import com.jetbrains.gymusserverjava.reports.services.RevenueReportsService
import com.jetbrains.gymusserverjava.sessions.repositories.SessionRepository
import com.jetbrains.gymusserverjava.store.repositories.SaleRepository
import org.springframework.stereotype.Service


@Service
class RevenueReportsServiceImpl(
    private val membershipRepository: MembershipRepository,
    private val saleRepository: SaleRepository,
    private val sessionRepository: SessionRepository,
) : RevenueReportsService {
    override fun totalRevenue(): Double =
        membershipRepository.totalRevenue + saleRepository.totalRevenue + sessionRepository.totalRevenue

    override fun monthlyRevenue(): Double =
        membershipRepository.monthlyRevenue + saleRepository.monthlyRevenue + sessionRepository.monthlyRevenue

    override fun totalStoreRevenue(): Double = saleRepository.totalRevenue

    override fun monthlyStoreRevenue(): Double = saleRepository.monthlyRevenue

    override fun totalSessionsRevenue(): Double = sessionRepository.totalRevenue

    override fun monthlySessionRevenue(): Double = sessionRepository.monthlyRevenue

    override fun totalMembershipsRevenue(): Double = membershipRepository.totalRevenue

    override fun monthlyMembershipsRevenue(): Double = membershipRepository.monthlyRevenue

    override fun totalActiveMembershipsRevenue(): Double = membershipRepository.totalActiveRevenue

    override fun monthlyActiveMembershipsRevenue(): Double =
        membershipRepository.monthlyActiveRevenue

}