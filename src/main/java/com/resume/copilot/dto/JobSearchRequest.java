package com.resume.copilot.dto;

public class JobSearchRequest {
    private String userId;
    private String targetRole;
    private String location = "Bangalore";  // Default

    // Getters/Setters
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getTargetRole() {
        return targetRole;
    }
    public void setTargetRole(String targetRole) {
        this.targetRole = targetRole;
    }
    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }
}
