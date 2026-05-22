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
    fun totalRevenue(): ResponseEntity<ApiResponse<Double>> =
        ResponseEntity.ok().body(ApiResponse(revenueReportsService.totalRevenue()))

    @GetMapping("monthly-revenue")
    fun monthlyRevenue(): ResponseEntity<ApiResponse<Double>> =
        ResponseEntity.ok().body(ApiResponse(revenueReportsService.monthlyRevenue()))

    @GetMapping("total-store-revenue")
    fun totalStoreRevenue(): ResponseEntity<ApiResponse<Double>> =
        ResponseEntity.ok().body(ApiResponse(revenueReportsService.totalStoreRevenue()))

    @GetMapping("monthly-store-revenue")
    fun monthlyStoreRevenue(): ResponseEntity<ApiResponse<Double>> =
        ResponseEntity.ok().body(ApiResponse(revenueReportsService.monthlyStoreRevenue()))

    @GetMapping("total-sessions-revenue")
    fun totalSessionsRevenue(): ResponseEntity<ApiResponse<Double>> =
        ResponseEntity.ok().body(ApiResponse(revenueReportsService.totalSessionsRevenue()))

    @GetMapping("monthly-sessions-revenue")
    fun monthlySessionRevenue(): ResponseEntity<ApiResponse<Double>> =
        ResponseEntity.ok().body(ApiResponse(revenueReportsService.monthlySessionRevenue()))

    @GetMapping("total-memberships-revenue")
    fun totalMembershipsRevenue(): ResponseEntity<ApiResponse<Double>> =
        ResponseEntity.ok().body(ApiResponse(revenueReportsService.totalMembershipsRevenue()))

    @GetMapping("monthly-memberships-revenue")
    fun monthlyMembershipsRevenue(): ResponseEntity<ApiResponse<Double>> =
        ResponseEntity.ok().body(ApiResponse(revenueReportsService.monthlyMembershipsRevenue()))

    @GetMapping("total-active-memberships-revenue")
    fun totalActiveMembershipsRevenue(): ResponseEntity<ApiResponse<Double>> =
        ResponseEntity.ok().body(ApiResponse(revenueReportsService.totalActiveMembershipsRevenue()))

    @GetMapping("monthly-active-memberships-revenue")
    fun monthlyActiveMembershipsRevenue(): ResponseEntity<ApiResponse<Double>> =
        ResponseEntity.ok()
                .body(ApiResponse(revenueReportsService.monthlyActiveMembershipsRevenue()))
}