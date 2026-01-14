package com.resume.copilot.service;

import com.resume.copilot.dto.JobSearchRequest;
import com.resume.copilot.dto.Profile;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;
// orchestrator.
// Planner Agent logic

@Service
public class FindJobService {
    private final ProfileService profileService;
    private final ChatClient chatClient;

    public FindJobService(ProfileService profileService, ChatClient.Builder builder) {
        this.profileService = profileService;
        this.chatClient = builder.build();
    }

    public String findJob(JobSearchRequest request) {
        Profile profile = profileService.getProfileByUserId(request.getUserId());
        return chatClient.prompt()
                .user("""
            Find %s jobs in %s matching this profile:
            
            USER: %s
            RESUME: %s
            LOCATION PREFERENCE: %s
            
            Provide: 
            1. Top 5 job matches with company names
            2. Skill gap analysis 
            3. Application strategy
            """.formatted(
                        request.getTargetRole(),
                        request.getLocation(),
                        request.getUserId(),
                        profile.getResumeContent(),
                        request.getLocation()
                ))
                .call()
                .content();
    }
}
