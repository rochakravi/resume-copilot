package com.resume.tools;

import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class ToolRegistry {

    private final Map<String, Tool> tools;

    public ToolRegistry(List<Tool> toolList) {
        this.tools = toolList.stream()
                .collect(Collectors.toMap(Tool::name, t -> t));
    }

    public Tool getTool(String name) {
        return tools.get(name);
    }
}

