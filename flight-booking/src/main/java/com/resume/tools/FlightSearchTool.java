package com.resume.tools;

import org.springframework.stereotype.Service;
import java.util.Map;

@Service
public class FlightSearchTool implements Tool {

    @Override
    public String name() {
        return "search_flights";
    }

    @Override
    public String execute(Map<String, Object> input) {

        String from = (String) input.get("from");
        String to = (String) input.get("to");
        String date = (String) input.get("date");

        // Mock response
        return "Indigo 6E-234 | " + from + " → " + to +
                " | 7:30 PM | ₹5,200";
    }
}
