package com.resume.copilot.controller;

import com.resume.copilot.service.IngestionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/ingestion")
public class IngestionController {

    private final IngestionService ingestionService;

    public IngestionController(IngestionService ingestionService) {
        this.ingestionService = ingestionService;
    }

    @GetMapping("/test")
    public String getTest() {
        return "Test Successful Tested";
    }


    @PostMapping("/ingestion")
    public ResponseEntity<String> ingestData(@RequestParam("file") MultipartFile file,
                                             @RequestParam("ingestType") String ingestType) throws Exception {
        byte[] fileContent = file.getBytes();
        ingestionService.ingestPdf(fileContent, file.getOriginalFilename(), ingestType);
        return ResponseEntity.ok("File uploaded successfully. ");
    }

}
