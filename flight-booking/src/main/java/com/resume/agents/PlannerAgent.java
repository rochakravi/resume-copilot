package com.resume.agents;

import com.resume.resoning.ReasoningResult;
import com.resume.resoning.ReasoningResultParser;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class PlannerAgent {

    private final ChatClient chatClient;

    public PlannerAgent(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

//    public ReasoningResult plan(String userInput, AgentState state) {
//        // same prompt style as LLMReasoningEngine
//        return llmReasoning(userInput, state);
//    }

    public ReasoningResult plan(String userInput, AgentState state) {

        String prompt = """
        You are a travel booking AI agent.

        Current state:
        %s

        User input:
        %s

        Available tools:
        1. search_flights(from, to, date)
        2. search_hotels(city, date)
        3. book_flight(flight)
        4. book_hotel(hotel)

        Rules:
        - Ask user if information is missing
        - Ask confirmation before booking
        - Respond ONLY in valid JSON

        JSON format:
        {
          "action": "ASK_USER | CALL_TOOL | COMPLETE",
          "tool": "tool_name_if_any",
          "arguments": {},
          "message": "text to user"
        }
        """.formatted(state, userInput);

        String response = chatClient.prompt(prompt).call().content();

        return ReasoningResultParser.parse(response);
    }
}

