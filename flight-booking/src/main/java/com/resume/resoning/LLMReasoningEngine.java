package com.resume.resoning;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Component;

@Component
public class LLMReasoningEngine {

    private final ChatClient chatClient;

    public LLMReasoningEngine(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    public ReasoningResult reason(String userInput, AgentState state) {

        String prompt = """
        You are a travel booking AI agent.

        Current state:
        %s

        User message:
        %s

        Available tools:
        - search_flights(from, to, date)
        - book_flight(flight)

        Respond ONLY in JSON.
        """.formatted(state, userInput);

        String llmResponse = chatClient.prompt(prompt).call().content();

        // parse JSON → ReasoningResult
        return ReasoningResultParser.parse(llmResponse);
    }
}

