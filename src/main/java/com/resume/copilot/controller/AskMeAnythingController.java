package com.resume.copilot.controller;

import com.resume.copilot.dto.ResumeQueryRequest;
import com.resume.copilot.service.IngestionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AskMeAnythingController {

    private final IngestionService ingestionService ;


    AskMeAnythingController(IngestionService ingestionService) {
        this.ingestionService = ingestionService;
    }

    @PostMapping("/ask-me-anything")
    public ResponseEntity<String> askResume(@RequestBody ResumeQueryRequest request) {

        String answer = ingestionService.askResume(request);
        return ResponseEntity.ok(answer);
    }
}
