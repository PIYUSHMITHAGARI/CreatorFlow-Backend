package com.example.demo.controller;

import com.example.demo.dto.CreatorInsightsResponse;
import com.example.demo.service.CreatorInsightsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/insights")
@CrossOrigin(origins = "*")
public class CreatorInsightsController {

    @Autowired
    private CreatorInsightsService
            creatorInsightsService;

    @GetMapping("/{userId}")
    public CreatorInsightsResponse
    getInsights(
            @PathVariable Long userId
    ) {

        return creatorInsightsService
                .getInsights(userId);
    }
}