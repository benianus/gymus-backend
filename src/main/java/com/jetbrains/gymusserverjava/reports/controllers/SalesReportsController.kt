package com.jetbrains.gymusserverjava.reports.controllers

import com.jetbrains.gymusserverjava.reports.services.SalesReportsService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/reports")
class SalesReportsController(private val reportsService: SalesReportsService) {
    @GetMapping("")
    fun totalSales(): Int {
        return reportsService.totalSales()
    }

    @GetMapping("")
    fun monthlySales(): Int {
        TODO("Not yet implemented")
    }

    @GetMapping("")
    fun totalStoreSales(): Int {
        TODO("Not yet implemented")
    }

    @GetMapping("")
    fun monthlyStoreSales(): Int {
        TODO("Not yet implemented")
    }

    @GetMapping("")
    fun totalSessionsSales(): Int {
        TODO("Not yet implemented")
    }

    @GetMapping("")
    fun monthlySessionSales(): Int {
        TODO("Not yet implemented")
    }

    @GetMapping("")
    fun totalMembershipsSales(): Int {
        TODO("Not yet implemented")
    }

    @GetMapping("")
    fun monthlyMembershipsSales(): Int {
        TODO("Not yet implemented")
    }

    @GetMapping("")
    fun totalActiveMembershipsSales(): Int {
        TODO("Not yet implemented")
    }

    @GetMapping("")
    fun monthlyActiveMembershipsSales(): Int {
        TODO("Not yet implemented")
    }

}