package com.resume.tools;

import org.springframework.stereotype.Service;
import java.util.Map;

@Service
public class BookingTool implements Tool {

    @Override
    public String name() {
        return "book_flight";
    }

    @Override
    public String execute(Map<String, Object> input) {

        String flight = (String) input.get("flight");

        // Mock booking
        return "✅ Booking confirmed!\nFlight: " + flight +
                "\nPNR: AI12345";
    }
}

