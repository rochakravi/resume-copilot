package com.resume.resoning;

public class AgentState {

    private boolean waitingForUserInput = true;

    public boolean isWaitingForUserInput() {
        return waitingForUserInput;
    }

    public void setWaitingForUserInput(boolean waitingForUserInput) {
        this.waitingForUserInput = waitingForUserInput;
    }
}
