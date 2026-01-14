package com.resume.copilot.controller;

import com.resume.copilot.service.IngestionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/ingestion")
public class IngestionController {

    private IngestionService ingestionService = null;

    public IngestionController(IngestionService ingestionService) {
        this.ingestionService = ingestionService;
    }

    @PostMapping("/upload-resume/{userId}")
    public ResponseEntity<String> uploadResume(
            @PathVariable String userId,
            @RequestParam MultipartFile file,
            @RequestParam String candidateId,
            @RequestParam(required = false) String role,
            @RequestParam(required = false) Integer experience,
            @RequestParam(required = false) String skills
    ) throws Exception {
        byte[] fileContent = file.getBytes();
        ingestionService.ingestResume(
                candidateId,
                file,
userId
//                file.getOriginalFilename(),
//                role,
//                experience,
//                skills
        );

        return ResponseEntity.ok("Resume ingested successfully for user  : " + userId);
    }

    @PostMapping("/upload-fact-sheet/{userId}")
    public ResponseEntity<String> uploadFactSheet(
            @RequestParam MultipartFile file,
            @RequestParam String candidateId,
            @PathVariable String userId
    ) throws Exception {
        byte[] fileContent = file.getBytes();
        ingestionService.ingestFacts(
                candidateId,
                file,
                userId
        );

        return ResponseEntity.ok("Fact sheet ingested successfully for user  : " + userId);
    }

}
