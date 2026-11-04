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
    fun totalSales(): ResponseEntity<ApiResponse<Int, Unit>> =
        ResponseEntity.ok().body(ApiResponse.Success(reportsService.totalSales()))

    @GetMapping("monthly-sales")
    fun monthlySales(): ResponseEntity<ApiResponse<Int, Unit>> =
        ResponseEntity.ok().body(ApiResponse.Success(reportsService.monthlySales()))

    @GetMapping("total-store-sales")
    fun totalStoreSales(): ResponseEntity<ApiResponse<Int, Unit>> =
        ResponseEntity.ok().body(ApiResponse.Success(reportsService.totalStoreSales()))

    @GetMapping("monthly-store-sales")
    fun monthlyStoreSales(): ResponseEntity<ApiResponse<Int, Unit>> =
        ResponseEntity.ok().body(ApiResponse.Success(reportsService.monthlyStoreSales()))

    @GetMapping("total-sessions-sales")
    fun totalSessionsSales(): ResponseEntity<ApiResponse<Int, Unit>> = ResponseEntity.ok().body(
        ApiResponse.Success(reportsService.totalSessionsSales())
    )

    @GetMapping("monthly-sessions-sales")
    fun monthlySessionSales(): ResponseEntity<ApiResponse<Int, Unit>> = ResponseEntity.ok().body(
        ApiResponse.Success(reportsService.monthlySessionSales())
    )

    @GetMapping("total-memberships-sales")
    fun totalMembershipsSales(): ResponseEntity<ApiResponse<Int, Unit>> = ResponseEntity.ok().body(
        ApiResponse.Success(reportsService.totalMembershipsSales())
    )

    @GetMapping("monthly-memberships-sales")
    fun monthlyMembershipsSales(): ResponseEntity<ApiResponse<Int, Unit>> =
        ResponseEntity.ok().body(
            ApiResponse.Success(reportsService.monthlyMembershipsSales())
        )

    @GetMapping("total-active-memberships-sales")
    fun totalActiveMembershipsSales(): ResponseEntity<ApiResponse<Int, Unit>> =
        ResponseEntity.ok().body(ApiResponse.Success(reportsService.totalActiveMembershipsSales()))

    @GetMapping("monthly-active-memberships-sales")
    fun monthlyActiveMembershipsSales(): ResponseEntity<ApiResponse<Int, Unit>> =
        ResponseEntity.ok()
            .body(ApiResponse.Success(reportsService.monthlyActiveMembershipsSales()))

}