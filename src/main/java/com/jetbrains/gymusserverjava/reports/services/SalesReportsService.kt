package com.jetbrains.gymusserverjava.reports.services

interface SalesReportsService {
    fun totalSales(): Int
    fun monthlySales(): Int
    fun totalStoreSales(): Int
    fun monthlyStoreSales(): Int
    fun totalSessionsSales(): Int
    fun monthlySessionSales(): Int
    fun totalMembershipsSales(): Int
    fun monthlyMembershipsSales(): Int
    fun totalActiveMembershipsSales(): Int
    fun monthlyActiveMembershipsSales(): Int
}
