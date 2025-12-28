package com.resume.copilot.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.resume.copilot.dto.AskMeAnythingResponse;
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
    public ResponseEntity<AskMeAnythingResponse> askResume(@RequestBody ResumeQueryRequest request) {

        AskMeAnythingResponse answer = null;
        try {
            answer = ingestionService.askResume(request);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok(answer);
    }
}
