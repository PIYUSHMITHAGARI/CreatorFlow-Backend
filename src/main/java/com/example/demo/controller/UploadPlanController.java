package com.example.demo.controller;

import com.example.demo.model.UploadPlan;
import com.example.demo.service.UploadPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/planner")
@CrossOrigin(origins = "http://localhost:5173")
public class UploadPlanController {

    @Autowired
    private UploadPlanService uploadPlanService;

    @PostMapping
    public UploadPlan createPlan(
            @RequestBody UploadPlan plan) {

        return uploadPlanService.createPlan(plan);
    }

    @GetMapping("/{userId}")
    public List<UploadPlan> getPlansByUser(
            @PathVariable Long userId) {

        return uploadPlanService.getPlansByUser(userId);
    }

    @GetMapping("/upcoming/{userId}")
    public List<UploadPlan> getUpcomingUploads(
            @PathVariable Long userId) {

        return uploadPlanService
                .getUpcomingUploads(userId);
    }

    @PutMapping("/{id}")
    public UploadPlan updatePlan(
            @PathVariable Long id,
            @RequestBody UploadPlan plan) {

        return uploadPlanService.updatePlan(id, plan);
    }

    @DeleteMapping("/{id}")
    public void deletePlan(
            @PathVariable Long id) {

        uploadPlanService.deletePlan(id);
    }
}