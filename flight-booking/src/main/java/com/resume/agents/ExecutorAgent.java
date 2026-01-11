package com.resume.agents;

import com.resume.resoning.ReasoningResult;
import com.resume.tools.Tool;
import com.resume.tools.ToolRegistry;
import org.springframework.stereotype.Service;

@Service
public class ExecutorAgent {

    private final ToolRegistry toolRegistry;

    public ExecutorAgent(ToolRegistry toolRegistry) {
        this.toolRegistry = toolRegistry;
    }

    public String execute(ReasoningResult decision, AgentState state) {

        Tool tool = toolRegistry.getTool(decision.getToolName());
        String output = tool.execute(decision.getToolArgs());

        state.setSelectedFlight(output);
        return output;
    }
}

