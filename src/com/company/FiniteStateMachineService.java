package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class FiniteStateMachineService {
    private static Integer numberOfBraces = 0;
    private static Integer numberOfParenthesis = 0;

    private static final String UNKNOWN_SYMBOL_ERROR = "Unknown symbol in input";

    /**
     * Вхідні дані:
     * Вхідний алфавіт
     * Всі стани
     * Початковий стан
     * State1 -> Action -> State2
     */
    public FiniteStateMachine readAndAnalyzeInputFile(String inputFile) {
        FiniteStateMachine finiteStateMachine = new FiniteStateMachine();
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            String allActions = reader.readLine();
            String allStates = reader.readLine();
            String startStateName = reader.readLine();

            finiteStateMachine.setAllStates(formAllStates(allStates));
            finiteStateMachine.setAlphabet(formAllActions(allActions));
            if (finiteStateMachine.getAllStates().get(startStateName) != null) {
                finiteStateMachine.setStartState(finiteStateMachine.getAllStates().get(startStateName));
            } else {
                throw new IllegalArgumentException(UNKNOWN_SYMBOL_ERROR);
            }
            formTransits(reader, finiteStateMachine.getAllStates(), finiteStateMachine.getAlphabet());
        } catch (IOException e) {
            throw new RuntimeException("Problems with reading file : ", e);
        }
        return finiteStateMachine;
    }

    private Set<State> formFinalStates(String finalStates, Map<String, State> allStates) {
        return Arrays.stream(finalStates.split(" "))
                .map(allStates::get)
                .collect(Collectors.toSet());
    }

    private Map<String, State> formAllStates(String states) {
        return Arrays.stream(states.split(" "))
                .map(State::new)
                .collect(Collectors.toMap(State::getName, state -> state));
    }

    private Map<String, Action> formAllActions(String actions) {
        Map<String, Action> alphabet = Arrays.stream(actions.split(" "))
                .map(action -> new Action(action, ActionPatterns.getFromName(action)))
                .collect(Collectors.toMap(Action::getName, action -> action));
        return alphabet;
    }

    private void formTransits(BufferedReader reader, Map<String, State> allStates, Map<String, Action> alphabet) {
        reader.lines().forEach(
                line -> {
                    State firstState = allStates.get(line.split(" ")[0]);
                    Action action = alphabet.get(line.split(" ")[1]);
                    State secondState = allStates.get(line.split(" ")[2]);
                    if (firstState == null || action == null || secondState == null) {
                        throw new IllegalArgumentException(UNKNOWN_SYMBOL_ERROR);
                    }
                    firstState.addNextState(action, secondState);
                    secondState.addPreviousState(action, firstState);
                });

    }
}
