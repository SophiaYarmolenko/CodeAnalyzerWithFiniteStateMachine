package com.company;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class State {
    private String name;
    Map<Action, State> nextStates = new HashMap<Action, State>();
    Map<Action, State> previousStates = new HashMap<>();

    public State(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Map<Action, State> getNextStates() {
        return nextStates;
    }

    public Map<Action, State> getPreviousStates() {
        return previousStates;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addNextState(Action action, State state) {
        nextStates.put(action, state);
    }

    public void addPreviousState(Action action, State state) {
        previousStates.put(action, state);
    }

    @Override
    public String toString() {
        return "State{" +
                "name='" + name + '\'' +
                "->" + nextStates.keySet() + nextStates.values().stream().map(State::getName).collect(Collectors.toList()) +
                '}';
    }
}