package com.jetbrains.gymusserverjava.reports

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/reports")
class ReportsController(private val reportsService: ReportsService)