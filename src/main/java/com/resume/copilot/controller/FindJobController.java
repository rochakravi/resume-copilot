package com.resume.copilot.controller;

import com.resume.copilot.dto.JobSearchRequest;
import com.resume.copilot.service.FindJobService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FindJobController {

    private final FindJobService findJobService;

    public FindJobController(FindJobService findJobService) {
        this.findJobService = findJobService;
    }

    @PostMapping("/find-job")
    public String findJob(@RequestBody JobSearchRequest searchRequest ) {
        return this.findJobService.findJob(searchRequest);
    }
}
