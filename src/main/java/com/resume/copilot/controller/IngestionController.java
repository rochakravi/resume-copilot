package com.resume.copilot.controller;

import com.resume.copilot.service.IngestionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/ingestion")
public class IngestionController {

    private IngestionService ingestionService = null;

    public IngestionController(IngestionService ingestionService) {
        this.ingestionService = ingestionService;
    }

    @PostMapping("/upload-resume")
    public ResponseEntity<String> uploadResume(
            @RequestParam MultipartFile file,
            @RequestParam String candidateId,
            @RequestParam(required = false) String role,
            @RequestParam(required = false) Integer experience,
            @RequestParam(required = false) String skills
    ) throws Exception {
        byte[] fileContent = file.getBytes();
        ingestionService.ingestResume(
                candidateId,
                file
//                file.getOriginalFilename(),
//                role,
//                experience,
//                skills
        );

        return ResponseEntity.ok("Resume ingested successfully");
    }
}
