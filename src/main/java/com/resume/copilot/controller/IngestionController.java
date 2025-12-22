package com.resume.copilot.controller;

import com.resume.copilot.dto.RequestDTO;
import com.resume.copilot.service.IngestionService;
import org.springframework.ai.document.Document;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/ingestion")
public class IngestionController {

    private IngestionService ingestionService = null;

    public IngestionController(IngestionService ingestionService) {
        this.ingestionService = ingestionService;
    }

    @GetMapping("/test")
    public String getTest() {
        return "Test Successful Tested";
    }


    @PostMapping("/pdf")
    public ResponseEntity<String> ingestData(@RequestParam("file") MultipartFile file,
                                             @RequestParam("ingestType") String ingestType) throws Exception {
        byte[] fileContent = file.getBytes();
        ingestionService.ingestPdf(fileContent, file.getOriginalFilename(), ingestType);
        return ResponseEntity.ok("File uploaded successfully. ");
    }

    @GetMapping("/retrieve-answer")
    public ResponseEntity<?> retrieveAnswer(@RequestParam(value = "question", required = false) String question) {
        if (question == null || question.isBlank()) {
            return ResponseEntity.badRequest().body("Missing required query parameter: question");
        }
        // Placeholder for actual answer retrieval logic
        String answer = "This is a placeholder answer for the question: " + question;
        return ResponseEntity.ok(answer);
    }

    @PostMapping("/retrieve-answer")
    public ResponseEntity<List<Document>> groundingV2(@RequestBody(required = false) RequestDTO requestDTO) {
        if (requestDTO == null || requestDTO.prompt() == null) {
            return ResponseEntity.badRequest().build();
        }
        List<Document> docs = this.ingestionService.retrieveAnswer(requestDTO);
        return ResponseEntity.ok(docs);
    }


}
