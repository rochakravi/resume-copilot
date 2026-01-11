package com.resume.resoning;


import java.util.Map;

public class ReasoningResult {

    public enum ActionType {
        ASK_USER,
        CALL_TOOL,
        COMPLETE
    }

    private ActionType action;
    private String toolName;
    private Map<String, Object> toolArgs;
    private String message;

    public ReasoningResult(ActionType action, String message) {
        this.action = action;
        this.message = message;
    }

    public ReasoningResult(String toolName, Map<String, Object> toolArgs) {
        this.action = ActionType.CALL_TOOL;
        this.toolName = toolName;
        this.toolArgs = toolArgs;
    }

    public ActionType getAction() {
        return action;
    }

    public String getToolName() {
        return toolName;
    }

    public Map<String, Object> getToolArgs() {
        return toolArgs;
    }

    public String getMessage() {
        return message;
    }

    // Convenience predicates used by the agent orchestration
    public boolean isAskUser() {
        return this.action == ActionType.ASK_USER;
    }

    public boolean isCallTool() {
        return this.action == ActionType.CALL_TOOL;
    }

    public boolean isComplete() {
        return this.action == ActionType.COMPLETE;
    }
}
