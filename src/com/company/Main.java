package com.company;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        FiniteStateMachineService finiteStateMachineService = new FiniteStateMachineService();
        FiniteStateMachine finiteStateMachine = finiteStateMachineService.readAndAnalyzeInputFile("finiteStateMachine.txt");

        AnalyzerService analyzerService = new AnalyzerService();
        List<String> words = new ArrayList<>();
        try {
            words = analyzerService.readAndSplitInputFile("input.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        analyzerService.analyzeCode(finiteStateMachine, words);
    }
}
