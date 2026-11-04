package com.jetbrains.gymusserverjava.reports.controllers

import com.jetbrains.gymusserverjava.reports.services.RevenueReportsService
import com.jetbrains.shared.dtos.ApiResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/reports")
class RevenueReportsController(private val revenueReportsService: RevenueReportsService) {
    @GetMapping("total-revenue")
    fun totalRevenue(): ResponseEntity<ApiResponse<Double, Unit>> =
        ResponseEntity.ok().body(ApiResponse.Success(revenueReportsService.totalRevenue()))

    @GetMapping("monthly-revenue")
    fun monthlyRevenue(): ResponseEntity<ApiResponse<Double, Unit>> =
        ResponseEntity.ok().body(ApiResponse.Success(revenueReportsService.monthlyRevenue()))

    @GetMapping("total-store-revenue")
    fun totalStoreRevenue(): ResponseEntity<ApiResponse<Double, Unit>> =
        ResponseEntity.ok().body(ApiResponse.Success(revenueReportsService.totalStoreRevenue()))

    @GetMapping("monthly-store-revenue")
    fun monthlyStoreRevenue(): ResponseEntity<ApiResponse<Double, Unit>> =
        ResponseEntity.ok().body(ApiResponse.Success(revenueReportsService.monthlyStoreRevenue()))

    @GetMapping("total-sessions-revenue")
    fun totalSessionsRevenue(): ResponseEntity<ApiResponse<Double, Unit>> =
        ResponseEntity.ok().body(ApiResponse.Success(revenueReportsService.totalSessionsRevenue()))

    @GetMapping("monthly-sessions-revenue")
    fun monthlySessionRevenue(): ResponseEntity<ApiResponse<Double, Unit>> =
        ResponseEntity.ok().body(ApiResponse.Success(revenueReportsService.monthlySessionRevenue()))

    @GetMapping("total-memberships-revenue")
    fun totalMembershipsRevenue(): ResponseEntity<ApiResponse<Double, Unit>> =
        ResponseEntity.ok()
            .body(ApiResponse.Success(revenueReportsService.totalMembershipsRevenue()))

    @GetMapping("monthly-memberships-revenue")
    fun monthlyMembershipsRevenue(): ResponseEntity<ApiResponse<Double, Unit>> =
        ResponseEntity.ok()
            .body(ApiResponse.Success(revenueReportsService.monthlyMembershipsRevenue()))

    @GetMapping("total-active-memberships-revenue")
    fun totalActiveMembershipsRevenue(): ResponseEntity<ApiResponse<Double, Unit>> =
        ResponseEntity.ok()
            .body(ApiResponse.Success(revenueReportsService.totalActiveMembershipsRevenue()))

    @GetMapping("monthly-active-memberships-revenue")
    fun monthlyActiveMembershipsRevenue(): ResponseEntity<ApiResponse<Double, Unit>> =
        ResponseEntity.ok()
            .body(ApiResponse.Success(revenueReportsService.monthlyActiveMembershipsRevenue()))

    @GetMapping("today-revenue")
    fun todayRevenue(): ResponseEntity<ApiResponse<Double, Unit>> =
        ResponseEntity.ok().body(ApiResponse.Success(revenueReportsService.todayRevenue()))
}