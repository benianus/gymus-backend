package com.jetbrains.gymusserverjava.reports.services

interface RevenueReportsService {
    fun totalRevenue(): Double
    fun monthlyRevenue(): Double
    fun totalStoreRevenue(): Double
    fun monthlyStoreRevenue(): Double
    fun totalSessionsRevenue(): Double
    fun monthlySessionRevenue(): Double
    fun totalMembershipsRevenue(): Double
    fun monthlyMembershipsRevenue(): Double
    fun totalActiveMembershipsRevenue(): Double
    fun monthlyActiveMembershipsRevenue(): Double
}
