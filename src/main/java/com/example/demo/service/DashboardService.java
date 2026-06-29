package com.example.demo.service;

import com.example.demo.dto.DashboardResponse;
import com.example.demo.model.Idea;
import com.example.demo.model.UploadPlan;
import com.example.demo.repository.IdeaRepository;
import com.example.demo.repository.UploadPlanRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class DashboardService {

    @Autowired
    private IdeaRepository ideaRepository;

    @Autowired
    private UploadPlanRepository uploadPlanRepository;

    public DashboardResponse getDashboardData(Long userId) {

        DashboardResponse response = new DashboardResponse();

        long totalIdeas =
                ideaRepository.countByUserId(userId);

        long plannedUploads =
                uploadPlanRepository.findByUserId(userId).size();

        long completedUploads =
                uploadPlanRepository.countByUserIdAndStatus(
                        userId,
                        "Completed"
                );

        int uploadStreak =
                (int) completedUploads;

        List<UploadPlan> upcomingUploads =
                uploadPlanRepository
                        .findTop3ByUserIdAndStatusOrderByUploadDateAsc(
                                userId,
                                "Upcoming"
                        );

        List<Integer> weeklyActivity =
                new ArrayList<>(
                        Collections.nCopies(7, 0)
                );

        List<UploadPlan> plans =
                uploadPlanRepository.findByUserId(userId);

        for (UploadPlan plan : plans) {

            try {

                String dateString =
                        plan.getUploadDate();

                if (dateString != null &&
                        !dateString.isEmpty()) {

                    LocalDate date =
                            LocalDate.parse(dateString);

                    int dayIndex =
                            date.getDayOfWeek().getValue() - 1;

                    weeklyActivity.set(
                            dayIndex,
                            weeklyActivity.get(dayIndex) + 1
                    );
                }

            } catch (Exception e) {

                System.out.println(
                        "Invalid Date : "
                                + plan.getUploadDate()
                );
            }
        }

        List<String> recentActivities =
                new ArrayList<>();

        List<Idea> latestIdeas =
                ideaRepository
                        .findTop5ByUserIdOrderByIdDesc(
                                userId
                        );

        for (Idea idea : latestIdeas) {

            recentActivities.add(
                    "Created idea: "
                            + idea.getTitle()
            );
        }

        List<UploadPlan> latestPlans =
                uploadPlanRepository
                        .findTop5ByUserIdOrderByIdDesc(
                                userId
                        );

        for (UploadPlan plan : latestPlans) {

            recentActivities.add(
                    "Scheduled "
                            + plan.getPlatform()
                            + " upload: "
                            + plan.getTitle()
            );
        }

        if (recentActivities.size() > 5) {

            recentActivities =
                    new ArrayList<>(
                            recentActivities.subList(
                                    0,
                                    5
                            )
                    );
        }

        response.setTotalIdeas(totalIdeas);
        response.setPlannedUploads(plannedUploads);
        response.setCompletedUploads(completedUploads);
        response.setUploadStreak(uploadStreak);
        response.setUpcomingUploads(upcomingUploads);
        response.setWeeklyActivity(weeklyActivity);
        response.setRecentActivities(recentActivities);

        return response;
    }
}