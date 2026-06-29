package com.example.demo.service;

import com.example.demo.model.Goal;
import com.example.demo.model.Idea;
import com.example.demo.model.UploadPlan;
import com.example.demo.repository.GoalRepository;
import com.example.demo.repository.IdeaRepository;
import com.example.demo.repository.UploadPlanRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoalService {

    @Autowired
    private GoalRepository goalRepository;

    @Autowired
    private IdeaRepository ideaRepository;

    @Autowired
    private UploadPlanRepository uploadPlanRepository;

    public Goal createGoal(Goal goal) {

        updateGoalProgress(goal);

        return goalRepository.save(goal);
    }

    public List<Goal> getGoals(Long userId) {

        List<Goal> goals =
                goalRepository.findByUserId(userId);

        for (Goal goal : goals) {

            updateGoalProgress(goal);

            goalRepository.save(goal);
        }

        return goals;
    }

    public void deleteGoal(Long id) {

        goalRepository.deleteById(id);
    }

    private void updateGoalProgress(
            Goal goal
    ) {

        Long userId = goal.getUserId();

        String type =
                goal.getGoalType();

        if (type == null) return;

        switch (type) {

            case "UPLOADS":

                long uploads =
                        uploadPlanRepository
                                .countByUserId(userId);

                goal.setCurrentValue(
                        (int) uploads
                );

                break;

            case "COMPLETED_UPLOADS":

                long completedUploads =
                        uploadPlanRepository
                                .countByUserIdAndStatus(
                                        userId,
                                        "Completed"
                                );

                goal.setCurrentValue(
                        (int) completedUploads
                );

                break;

            case "IDEAS":

                long ideas =
                        ideaRepository
                                .countByUserId(userId);

                goal.setCurrentValue(
                        (int) ideas
                );

                break;

            case "COMPLETED_IDEAS":

                long completedIdeas =
                        ideaRepository
                                .countByUserIdAndStatus(
                                        userId,
                                        "Completed"
                                );

                goal.setCurrentValue(
                        (int) completedIdeas
                );

                break;

            default:
                break;
        }
    }
}