package com.resume.copilot.dto;

import java.util.List;
import java.util.Map;

public record AskMeAnythingResponse(
        String answer,
        List<String> highlights,
        Map<String, Object> meta
) { }
