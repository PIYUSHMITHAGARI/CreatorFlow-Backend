package com.example.demo.service;

import com.example.demo.dto.CreatorInsightsResponse;
import com.example.demo.model.Idea;
import com.example.demo.model.UploadPlan;
import com.example.demo.repository.IdeaRepository;
import com.example.demo.repository.UploadPlanRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CreatorInsightsService {

    @Autowired
    private IdeaRepository ideaRepository;

    @Autowired
    private UploadPlanRepository uploadPlanRepository;

    public CreatorInsightsResponse getInsights(Long userId) {

        CreatorInsightsResponse response =
                new CreatorInsightsResponse();

        List<Idea> ideas =
                ideaRepository.findByUserId(userId);

        List<UploadPlan> uploads =
                uploadPlanRepository.findByUserId(userId);

        // =========================
        // Ideas Statistics
        // =========================

        long totalIdeas = ideas.size();

        long completedIdeas =
                ideas.stream()
                        .filter(i ->
                                "Completed".equalsIgnoreCase(
                                        i.getStatus()))
                        .count();

        long pendingIdeas =
                totalIdeas - completedIdeas;

        response.setTotalIdeas(totalIdeas);
        response.setCompletedIdeas(completedIdeas);
        response.setPendingIdeas(pendingIdeas);

        // =========================
        // Upload Statistics
        // =========================

        long totalUploads = uploads.size();

        long completedUploads =
                uploads.stream()
                        .filter(u ->
                                "Completed".equalsIgnoreCase(
                                        u.getStatus()))
                        .count();

        long missedUploads =
                uploads.stream()
                        .filter(u ->
                                "Missed".equalsIgnoreCase(
                                        u.getStatus()))
                        .count();

        response.setTotalUploads(totalUploads);
        response.setCompletedUploads(completedUploads);
        response.setMissedUploads(missedUploads);

        // =========================
        // Consistency Score
        // =========================

        int consistencyScore = 0;

        if (totalUploads > 0) {

            consistencyScore =
                    (int) ((completedUploads * 100)
                            / totalUploads);
        }

        response.setConsistencyScore(
                consistencyScore);

        // =========================
        // Idea Completion Score
        // =========================

        int ideaCompletionScore = 0;

        if (totalIdeas > 0) {

            ideaCompletionScore =
                    (int) ((completedIdeas * 100)
                            / totalIdeas);
        }

        response.setIdeaCompletionScore(
                ideaCompletionScore);

        // =========================
        // Upload Streak
        // =========================

        response.setUploadStreak(
                (int) completedUploads
        );

        // =========================
        // Creator Health Score
        // =========================

        int creatorHealthScore =
                (consistencyScore
                        + ideaCompletionScore)
                        / 2;

        response.setCreatorHealthScore(
                creatorHealthScore);

        // =========================
        // Category Distribution
        // =========================

        Map<String, Long> categoryMap =
                ideas.stream()
                        .collect(
                                Collectors.groupingBy(
                                        Idea::getCategory,
                                        Collectors.counting()
                                )
                        );

        response.setCategoryDistribution(
                categoryMap);

        // =========================
        // Most Used Category
        // =========================

        String mostUsedCategory =
                categoryMap.entrySet()
                        .stream()
                        .max(
                                Map.Entry.comparingByValue()
                        )
                        .map(Map.Entry::getKey)
                        .orElse("None");

        response.setMostUsedCategory(
                mostUsedCategory);

        // =========================
        // REAL Weekly Productivity
        // =========================

        List<Integer> weeklyProductivity =
                new ArrayList<>(
                        Collections.nCopies(7, 0)
                );

        for (UploadPlan upload : uploads) {

            try {

                if (upload.getUploadDate() != null
                        && !upload.getUploadDate().isEmpty()) {

                    LocalDate date =
                            LocalDate.parse(
                                    upload.getUploadDate()
                            );

                    int dayIndex =
                            date.getDayOfWeek()
                                    .getValue() - 1;

                    weeklyProductivity.set(
                            dayIndex,
                            weeklyProductivity.get(dayIndex) + 1
                    );
                }

            } catch (Exception e) {

                System.out.println(
                        "Invalid Date : "
                                + upload.getUploadDate()
                );
            }
        }

        response.setWeeklyProductivity(
                weeklyProductivity
        );

        // =========================
        // Weekly Report
        // =========================

        String report =
                "You created "
                        + totalIdeas
                        + " ideas, completed "
                        + completedUploads
                        + " uploads and your strongest category is "
                        + mostUsedCategory + ".";

        response.setWeeklyReport(report);

        return response;
    }
}