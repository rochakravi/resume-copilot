package com.resume.copilot.dto;

import java.util.List;

public class Profile {
    private String userId;
    private String resumeContent;
    private String candidateId;
    private List<String> skills;  // Extract later

    public Profile() {
    }

    public Profile(String userId, String resumeContent, String candidateId) {
        this.userId = userId;
        this.resumeContent = resumeContent;
        this.candidateId = candidateId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getResumeContent() {
        return resumeContent;
    }

    public void setResumeContent(String resumeContent) {
        this.resumeContent = resumeContent;
    }

    public String getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(String candidateId) {
        this.candidateId = candidateId;
    }

    public List<String> getSkills() {
        return skills;
    }

    public void setSkills(List<String> skills) {
        this.skills = skills;
    }
}
