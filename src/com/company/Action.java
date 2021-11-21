package com.company;

public class Action {
    private String name;
    private ActionPatterns pattern;

    public Action(String name, ActionPatterns pattern) {
        this.name = name;
        this.pattern = pattern;
    }

    public String getName() {
        return name;
    }

    public boolean checkPatternMatcher(String word){
        return word.matches(pattern.getValue());
    }

    @Override
    public String toString() {
        return name;
    }
}