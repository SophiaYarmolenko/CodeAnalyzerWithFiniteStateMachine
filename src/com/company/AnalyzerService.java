package com.company;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class AnalyzerService {
    private static final String UNKNOWN_SYMBOL_ERROR = "Unknown symbol in input";

    public List<String> readAndSplitInputFile(String inputFilePath) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(inputFilePath));
        String inputFile = new String(encoded);
        inputFile = inputFile.replaceAll(Patterns.COMMENT, "~").trim().replaceAll("\\s+", "~");

        inputFile = inputFile.replaceAll("\\{", "~{~");
        inputFile = inputFile.replaceAll("\\}", "~}~");
        inputFile = inputFile.replaceAll("\\[", "~[~");
        inputFile = inputFile.replaceAll("\\]", "~]~");
        inputFile = inputFile.replaceAll("\\(", "~(~");
        inputFile = inputFile.replaceAll("\\)", "~)~");
        inputFile = inputFile.replaceAll("\\;", "~;~");
        inputFile = inputFile.replaceAll("\\,", "~,~");
        inputFile = inputFile.replaceAll("\\.", "~.~");
        inputFile = inputFile.replaceAll("\\==", "~==~");
        inputFile = inputFile.replaceAll("\\!=", "~!=~");
        inputFile = inputFile.replaceAll("\\!", "~!~");
        inputFile = inputFile.replaceAll("\\=", "~=~");
        inputFile = inputFile.replaceAll("\\>", "~>~");
        inputFile = inputFile.replaceAll("\\>=", "~>=~");
        inputFile = inputFile.replaceAll("\\<", "~<~");
        inputFile = inputFile.replaceAll("\\<=", "~<=~");

        return Arrays.asList(inputFile.split("~+"));
        //amen
    }

    public void analyzeCode(FiniteStateMachine finiteStateMachine, List<String> actions) {
        State currentState = finiteStateMachine.getStartState();
        for (String action : actions) {
            Map<Action, State> nextStates = currentState.getNextStates();
            boolean match = false;
            for(Action stateAction : nextStates.keySet()){
                match = stateAction.checkPatternMatcher(action);
                if (match) {
                    System.out.println(action + " - " + stateAction.getName());
                    currentState = currentState.nextStates.get(stateAction);
                    break;
                }
            }
            if(!match){
                throw new IllegalArgumentException(UNKNOWN_SYMBOL_ERROR + " - \"" + action + "\"");
            }
        }
    }

}
