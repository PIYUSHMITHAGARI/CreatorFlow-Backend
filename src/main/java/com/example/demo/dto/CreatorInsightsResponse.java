package com.example.demo.dto;

import java.util.List;
import java.util.Map;

public class CreatorInsightsResponse {

    // Top Cards

    private int creatorHealthScore;
    private int consistencyScore;
    private int uploadStreak;
    private int ideaCompletionScore;

    // Ideas

    private long totalIdeas;
    private long completedIdeas;
    private long pendingIdeas;

    // Uploads

    private long totalUploads;
    private long completedUploads;
    private long missedUploads;

    // Charts

    private Map<String, Long> categoryDistribution;

    private List<Integer> weeklyProductivity;

    // Weekly Report

    private String mostUsedCategory;
    private String weeklyReport;

    // Getters & Setters

    public int getCreatorHealthScore() {
        return creatorHealthScore;
    }

    public void setCreatorHealthScore(int creatorHealthScore) {
        this.creatorHealthScore = creatorHealthScore;
    }

    public int getConsistencyScore() {
        return consistencyScore;
    }

    public void setConsistencyScore(int consistencyScore) {
        this.consistencyScore = consistencyScore;
    }

    public int getUploadStreak() {
        return uploadStreak;
    }

    public void setUploadStreak(int uploadStreak) {
        this.uploadStreak = uploadStreak;
    }

    public int getIdeaCompletionScore() {
        return ideaCompletionScore;
    }

    public void setIdeaCompletionScore(int ideaCompletionScore) {
        this.ideaCompletionScore = ideaCompletionScore;
    }

    public long getTotalIdeas() {
        return totalIdeas;
    }

    public void setTotalIdeas(long totalIdeas) {
        this.totalIdeas = totalIdeas;
    }

    public long getCompletedIdeas() {
        return completedIdeas;
    }

    public void setCompletedIdeas(long completedIdeas) {
        this.completedIdeas = completedIdeas;
    }

    public long getPendingIdeas() {
        return pendingIdeas;
    }

    public void setPendingIdeas(long pendingIdeas) {
        this.pendingIdeas = pendingIdeas;
    }

    public long getTotalUploads() {
        return totalUploads;
    }

    public void setTotalUploads(long totalUploads) {
        this.totalUploads = totalUploads;
    }

    public long getCompletedUploads() {
        return completedUploads;
    }

    public void setCompletedUploads(long completedUploads) {
        this.completedUploads = completedUploads;
    }

    public long getMissedUploads() {
        return missedUploads;
    }

    public void setMissedUploads(long missedUploads) {
        this.missedUploads = missedUploads;
    }

    public Map<String, Long> getCategoryDistribution() {
        return categoryDistribution;
    }

    public void setCategoryDistribution(
            Map<String, Long> categoryDistribution) {
        this.categoryDistribution = categoryDistribution;
    }

    public List<Integer> getWeeklyProductivity() {
        return weeklyProductivity;
    }

    public void setWeeklyProductivity(
            List<Integer> weeklyProductivity) {
        this.weeklyProductivity = weeklyProductivity;
    }

    public String getMostUsedCategory() {
        return mostUsedCategory;
    }

    public void setMostUsedCategory(
            String mostUsedCategory) {
        this.mostUsedCategory = mostUsedCategory;
    }

    public String getWeeklyReport() {
        return weeklyReport;
    }

    public void setWeeklyReport(String weeklyReport) {
        this.weeklyReport = weeklyReport;
    }
}