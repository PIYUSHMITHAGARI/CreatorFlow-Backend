package com.example.demo.dto;

import com.example.demo.model.UploadPlan;

import java.util.List;

public class DashboardResponse {

    private long totalIdeas;

    private long plannedUploads;

    private long completedUploads;

    private int uploadStreak;

    private List<Integer> weeklyActivity;

    private List<UploadPlan> upcomingUploads;

    private List<String> recentActivities;

    public long getTotalIdeas() {
        return totalIdeas;
    }

    public void setTotalIdeas(long totalIdeas) {
        this.totalIdeas = totalIdeas;
    }

    public long getPlannedUploads() {
        return plannedUploads;
    }

    public void setPlannedUploads(long plannedUploads) {
        this.plannedUploads = plannedUploads;
    }

    public long getCompletedUploads() {
        return completedUploads;
    }

    public void setCompletedUploads(long completedUploads) {
        this.completedUploads = completedUploads;
    }

    public int getUploadStreak() {
        return uploadStreak;
    }

    public void setUploadStreak(int uploadStreak) {
        this.uploadStreak = uploadStreak;
    }

    public List<Integer> getWeeklyActivity() {
        return weeklyActivity;
    }

    public void setWeeklyActivity(
            List<Integer> weeklyActivity) {
        this.weeklyActivity = weeklyActivity;
    }

    public List<UploadPlan> getUpcomingUploads() {
        return upcomingUploads;
    }

    public void setUpcomingUploads(
            List<UploadPlan> upcomingUploads) {
        this.upcomingUploads = upcomingUploads;
    }

    public List<String> getRecentActivities() {
        return recentActivities;
    }

    public void setRecentActivities(
            List<String> recentActivities) {
        this.recentActivities = recentActivities;
    }
}