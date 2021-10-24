package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class FiniteStateMachineService {
    private static final String UNKNOWN_SYMBOL_ERROR = "Unknown symbol in input";

    /**
     * Вхідні дані:
     * Розмір вхідного алфавіту
     * Всі стани
     * Початковий стан
     * фінальні стани по черзі
     * State1 -> Action -> State2
     */
    public FiniteStateMachine readAndAnalyzeInputFile(String inputFile) {
        FiniteStateMachine finiteStateMachine = new FiniteStateMachine();
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            int alphabetSize = Integer.parseInt(reader.readLine());
            String allStates = reader.readLine();
            String startStateName = reader.readLine();
            String finalStates = reader.readLine();

            finiteStateMachine.setAllStates(formAllStates(allStates));
            finiteStateMachine.setAlphabet(formAlphabet(alphabetSize));
            if (finiteStateMachine.getAllStates().get(startStateName) != null) {
                finiteStateMachine.setStartState(finiteStateMachine.getAllStates().get(startStateName));
            } else {
                throw new IllegalArgumentException(UNKNOWN_SYMBOL_ERROR);
            }
            finiteStateMachine.setFinalStates(formFinalStates(finalStates, finiteStateMachine.getAllStates()));
            formTransits(reader, finiteStateMachine.getAllStates(), finiteStateMachine.getAlphabet());
        } catch (IOException e) {
            throw new RuntimeException("Problems with reading file : ", e);
        }
        return finiteStateMachine;
    }

    public Set<String> findAllConsumedWords(FiniteStateMachine finiteStateMachine) {
        return addLetter("",
                finiteStateMachine.getStartState(),
                finiteStateMachine.getFinalStates(),
                new HashSet<>());
    }

    private Set<String> addLetter(String wordStart, State state, Set<State> finalStates, Set<String> allWords) {
        state.getNextStates().forEach((action, nextState) ->
        {
            String wordStart1 = wordStart + action.getName();
            if (finalStates.contains(nextState)) {
                allWords.add(wordStart1);
            }
            addLetter(wordStart1, nextState, finalStates, allWords);
        });
        return allWords;
    }

    private Map<String, Action> formAlphabet(int size) {
        Map<String, Action> alphabet = new HashMap<>();
        for (int i = 0; i < size; i++) {
            char currentLetter = (char) ('a' + i);
            Action action = new Action(String.valueOf(currentLetter));
            alphabet.put(action.getName(), action);
        }
        return alphabet;
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

    private void formTransits(BufferedReader reader, Map<String, State> allStates, Map<String, Action> alphabet) {
        reader.lines().forEach(
                line -> {
                    State firstState = allStates.get(line.split(" ")[0]);
                    Action action = alphabet.get(line.split(" ")[1]);
                    State secondState = allStates.get(line.split(" ")[2]);
                    if (firstState == null || action == null || secondState == null || firstState.equals(secondState)) {
                        throw new IllegalArgumentException(UNKNOWN_SYMBOL_ERROR);
                    }
                    firstState.addNextState(action, secondState);
                    secondState.addPreviousState(action, firstState);
                });

    }
}
