package com.example.demo.controller;

import com.example.demo.dto.DashboardResponse;
import com.example.demo.service.DashboardService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dashboard")

@CrossOrigin(
        origins = "http://localhost:5173"
)
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

    @GetMapping("/{userId}")
    public DashboardResponse
    getDashboardData(
            @PathVariable Long userId
    ) {

        return dashboardService
                .getDashboardData(userId);
    }
}