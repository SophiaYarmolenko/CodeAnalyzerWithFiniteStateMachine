package com.company;

import java.util.Map;
import java.util.Set;

public class FiniteStateMachine {
    private State startState;
    private Set<State> finalStates;
    private Map<String, Action> alphabet;
    private Map<String, State> allStates;

    public FiniteStateMachine(){}

    public void setStartState(State startState) {
        this.startState = startState;
    }

    public void setAlphabet(Map<String, Action> alphabet) {
        this.alphabet = alphabet;
    }

    public void setFinalStates(Set<State> finalStates) {
        this.finalStates = finalStates;
    }

    public void setAllStates(Map<String, State> allStates) {
        this.allStates = allStates;
    }

    public State getStartState() {
        return startState;
    }

    public Set<State> getFinalStates() {
        return finalStates;
    }

    public Map<String, Action> getAlphabet() {
        return alphabet;
    }

    public Map<String, State> getAllStates() {
        return allStates;
    }

    @Override
    public String toString() {
        return "FiniteStateMachine{" +
                "startState=" + startState +
                ", finalStates=" + finalStates +
                ", alphabet=" + alphabet +
                ", allStates=" + allStates +
                '}';
    }
}