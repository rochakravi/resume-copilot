package com.resume.agents;

public class AgentState {

//    private String userGoal;
//    private boolean waitingForUserInput;


    private boolean waitingForUserDetails = true;
    private boolean waitingForConfirmation = false;
    private String selectedFlight;

    public boolean isWaitingForUserDetails() {
        return waitingForUserDetails;
    }

    public void setWaitingForUserDetails(boolean waitingForUserDetails) {
        this.waitingForUserDetails = waitingForUserDetails;
    }

    public boolean isWaitingForConfirmation() {
        return waitingForConfirmation;
    }

    public void setWaitingForConfirmation(boolean waitingForConfirmation) {
        this.waitingForConfirmation = waitingForConfirmation;
    }

    public String getSelectedFlight() {
        return selectedFlight;
    }

    public void setSelectedFlight(String selectedFlight) {
        this.selectedFlight = selectedFlight;
    }

//    public AgentState(String userGoal) {
//        this.userGoal = userGoal;
//        this.waitingForUserInput = true;
//    }
//
//    public String getUserGoal() {
//        return userGoal;
//    }
//
//    public boolean isWaitingForUserInput() {
//        return waitingForUserInput;
//    }
//
//    public void setWaitingForUserInput(boolean waitingForUserInput) {
//        this.waitingForUserInput = waitingForUserInput;
//    }
}
