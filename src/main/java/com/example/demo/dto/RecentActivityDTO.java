package com.example.demo.dto;

public class RecentActivityDTO {

    private String type;
    private String title;
    private String time;

    public RecentActivityDTO() {
    }

    public RecentActivityDTO(
            String type,
            String title,
            String time) {

        this.type = type;
        this.title = title;
        this.time = time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}