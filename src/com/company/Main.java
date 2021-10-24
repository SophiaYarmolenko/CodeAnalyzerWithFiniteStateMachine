package com.company;

public class Main {

    public static void main(String[] args) {
        FiniteStateMachineService finiteStateMachineService = new FiniteStateMachineService();
        FiniteStateMachine finiteStateMachine = finiteStateMachineService.readAndAnalyzeInputFile("input.txt");
        System.out.println(finiteStateMachineService.findAllConsumedWords(finiteStateMachine));
    }
}
