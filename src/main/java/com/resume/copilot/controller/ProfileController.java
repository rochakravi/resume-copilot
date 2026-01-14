package com.resume.copilot.controller;

import com.resume.copilot.dto.Profile;
import com.resume.copilot.service.ProfileService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProfileController {

    private final ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @PostMapping("/profile/{userId}")
    public Profile createProfile(@PathVariable String userId) {
        return profileService.getProfileByUserId(userId);
    }
}
