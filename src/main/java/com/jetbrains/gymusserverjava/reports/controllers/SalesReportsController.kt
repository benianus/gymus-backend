package com.jetbrains.gymusserverjava.reports.controllers

import com.jetbrains.gymusserverjava.reports.services.SalesReportsService
import com.jetbrains.shared.dtos.ApiResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/reports")
class SalesReportsController(private val reportsService: SalesReportsService) {
    @GetMapping("total-sales")
    fun totalSales(): ResponseEntity<ApiResponse<Int>> =
        ResponseEntity.ok().body(ApiResponse(reportsService.totalSales()))

    @GetMapping("monthly-sales")
    fun monthlySales(): ResponseEntity<ApiResponse<Int>> =
        ResponseEntity.ok().body(ApiResponse(reportsService.monthlySales()))

    @GetMapping("total-store-sales")
    fun totalStoreSales(): ResponseEntity<ApiResponse<Int>> =
        ResponseEntity.ok().body(ApiResponse(reportsService.totalStoreSales()))

    @GetMapping("monthly-store-sales")
    fun monthlyStoreSales(): ResponseEntity<ApiResponse<Int>> =
        ResponseEntity.ok().body(ApiResponse(reportsService.monthlyStoreSales()))

    @GetMapping("total-sessions-sales")
    fun totalSessionsSales(): ResponseEntity<ApiResponse<Int>> = ResponseEntity.ok().body(
        ApiResponse(reportsService.totalSessionsSales())
    )

    @GetMapping("monthly-sessions-sales")
    fun monthlySessionSales(): ResponseEntity<ApiResponse<Int>> = ResponseEntity.ok().body(
        ApiResponse(reportsService.monthlySessionSales())
    )

    @GetMapping("total-memberships-sales")
    fun totalMembershipsSales(): ResponseEntity<ApiResponse<Int>> = ResponseEntity.ok().body(
        ApiResponse(reportsService.totalMembershipsSales())
    )

    @GetMapping("monthly-memberships-sales")
    fun monthlyMembershipsSales(): ResponseEntity<ApiResponse<Int>> = ResponseEntity.ok().body(
        ApiResponse(reportsService.monthlyMembershipsSales())
    )

    @GetMapping("total-active-memberships-sales")
    fun totalActiveMembershipsSales(): ResponseEntity<ApiResponse<Int>> =
        ResponseEntity.ok().body(ApiResponse(reportsService.totalActiveMembershipsSales()))

    @GetMapping("monthly-active-memberships-sales")
    fun monthlyActiveMembershipsSales(): ResponseEntity<ApiResponse<Int>> =
        ResponseEntity.ok().body(ApiResponse(reportsService.monthlyActiveMembershipsSales()))

}