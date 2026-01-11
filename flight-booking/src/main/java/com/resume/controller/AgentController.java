package com.resume.controller;

import com.resume.agents.AgentResponse;
import com.resume.agents.TravelAgent;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/agent")
public class AgentController {

    private final TravelAgent travelAgent;

    public AgentController(TravelAgent travelAgent) {
        this.travelAgent = travelAgent;
    }

    @PostMapping("/chat")
    public AgentResponse chat(@RequestBody String message) {
        return travelAgent.handle(message);
    }

    @GetMapping("/status")
    public String status() {
        return "Agent is running";
    }
}
