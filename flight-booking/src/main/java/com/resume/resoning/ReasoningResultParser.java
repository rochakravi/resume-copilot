package com.resume.resoning;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ReasoningResultParser {

    private static final ObjectMapper mapper = new ObjectMapper();

    public static ReasoningResult parse(String json) {
        try {
            return mapper.readValue(json, ReasoningResult.class);
        } catch (Exception e) {
            throw new RuntimeException("Invalid LLM response: " + json);
        }
    }
}

