package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "upload_plans")
public class UploadPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String platform;

    private String uploadDate;

    private String uploadTime;

    private String status;

    private Long userId;

    public UploadPlan() {
    }

    public UploadPlan(Long id, String title, String platform,
                      String uploadDate, String uploadTime,
                      String status, Long userId) {
        this.id = id;
        this.title = title;
        this.platform = platform;
        this.uploadDate = uploadDate;
        this.uploadTime = uploadTime;
        this.status = status;
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(String uploadDate) {
        this.uploadDate = uploadDate;
    }

    public String getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(String uploadTime) {
        this.uploadTime = uploadTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}