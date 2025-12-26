package com.resume.copilot.dto;

public record ResumeQueryRequest(
        String candidateId,
        String question
) {}
