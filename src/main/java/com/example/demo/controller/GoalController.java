package com.example.demo.controller;

import com.example.demo.model.Goal;
import com.example.demo.service.GoalService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/goals")
@CrossOrigin(origins = "http://localhost:5173")
public class GoalController {

    @Autowired
    private GoalService goalService;

    @PostMapping
    public Goal createGoal(
            @RequestBody Goal goal
    ) {
        return goalService.createGoal(goal);
    }

    @GetMapping("/{userId}")
    public List<Goal> getGoals(
            @PathVariable Long userId
    ) {
        return goalService.getGoals(userId);
    }

    @DeleteMapping("/{id}")
    public void deleteGoal(
            @PathVariable Long id
    ) {
        goalService.deleteGoal(id);
    }
}