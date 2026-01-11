package com.resume.agents;

import com.resume.resoning.ReasoningEngine;
import com.resume.resoning.ReasoningResult;
import com.resume.tools.ToolRegistry;
import org.springframework.stereotype.Service;

// orchestrator agent
@Service
public class TravelAgent {

    private final PlannerAgent plannerAgent;
    private final ExecutorAgent executorAgent;
    private final ReasoningEngine reasoningEngine;
    private final ToolRegistry toolRegistry;
    private AgentState state;

    public TravelAgent(ReasoningEngine reasoningEngine, ToolRegistry toolRegistry, PlannerAgent plannerAgent, ExecutorAgent executorAgent) {
        this.reasoningEngine = reasoningEngine;
        this.plannerAgent = plannerAgent;
        this.executorAgent = executorAgent;
        this.toolRegistry = toolRegistry;
    }

    public AgentResponse handle(String userInput) {

//        ReasoningResult decision = reasoningEngine.reason(userInput, state);
        ReasoningResult decision = plannerAgent.plan(userInput, state);

        if (decision.isAskUser()) {
            return new AgentResponse(decision.getMessage());
        }

        if (decision.isCallTool()) {
            String result = executorAgent.execute(decision, state);
            return new AgentResponse(result);
        }
        return new AgentResponse(decision.getMessage());



//        switch (decision.getAction()) {
//
//            case ASK_USER:
//                state = new AgentState();
//                return new AgentResponse(decision.getMessage());
//
//            case CALL_TOOL:
//                Tool tool = toolRegistry.getTool(decision.getToolName());
//                String toolResult = tool.execute(decision.getToolArgs());
//
//                // After flight search
//                if (decision.getToolName().equals("search_flights")) {
//                    state.setWaitingForUserDetails(false);
//                    state.setWaitingForConfirmation(true);
//                    state.setSelectedFlight(toolResult);
//
//                    return new AgentResponse(
//                            "I found this flight:\n" + toolResult +
//                                    "\n\nDo you want me to book it? (yes/no)"
//                    );
//                }
//
//                // After booking
//                if (decision.getToolName().equals("book_flight")) {
//                    state = null; // reset agent
//                    return new AgentResponse(toolResult);
//                }
//
//            case COMPLETE:
//                return new AgentResponse(decision.getMessage());
//
//            default:
//                return new AgentResponse("I'm not sure how to proceed.");
//        }
//
//        // First interaction
//        if (state == null) {
//            state = new AgentState(userInput);
//            return new AgentResponse(
//                    "I can help you book a trip. From which city are you traveling and on which date?"
//            );
//        }
//
//        // Waiting for missing info
//        if (state.isWaitingForUserInput()) {
//            state.setWaitingForUserInput(false);
//
//            return new AgentResponse(
//                    "Thanks. I will now search for flights. (Next step: add flight search tool)"
//            );
//        }
//
//        return new AgentResponse("Task already in progress.");
    }
}
