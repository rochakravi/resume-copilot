package com.resume.resoning;

import com.resume.agents.AgentState;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ReasoningEngine {

    public ReasoningResult reason(String userInput, AgentState state) {

        // First interaction
        if (state == null) {
            return new ReasoningResult(
                    ReasoningResult.ActionType.ASK_USER,
                    "I can help you book a trip. From which city are you traveling and on which date?"
            );
        }

        // Waiting for travel details
        if (state.isWaitingForUserDetails()) {

            return new ReasoningResult(
                    "search_flights",
                    Map.of(
                            "from", "Delhi",
                            "to", "Bangalore",
                            "date", "Tomorrow"
                    )
            );
        }

        // Waiting for confirmation
        if (state.isWaitingForConfirmation()) {

            if (userInput.equalsIgnoreCase("yes")) {
                return new ReasoningResult(
                        "book_flight",
                        Map.of("flight", state.getSelectedFlight())
                );
            } else {
                return new ReasoningResult(
                        ReasoningResult.ActionType.COMPLETE,
                        "Okay, I have cancelled the booking request."
                );
            }
        }

        return new ReasoningResult(
                ReasoningResult.ActionType.COMPLETE,
                "Your trip booking process is complete."
        );
    }
}

