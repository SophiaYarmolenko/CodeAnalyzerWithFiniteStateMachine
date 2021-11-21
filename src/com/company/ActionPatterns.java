package com.company;

public enum ActionPatterns {
    DECIMAL_LITERAL(Patterns.DECIMAL_LITERAL, "decimal_literal"),
    BOOL_LITERAL(Patterns.BOOL_LITERAL, "bool_literal"),
    STRING_LITERAL(Patterns.STRING_LITERAL, "string_literal"),
    COMMENT(Patterns.COMMENT, "comment"),
    KEYWORD(Patterns.KEYWORD, "keyword"),
    IDENTIFIER(Patterns.IDENTIFIER, "identifier"),
    CHAR(Patterns.CHAR, "char"),
    OPERATORS(Patterns.OPERATORS, "operator"),
    DOTE(Patterns.DOTE, "dote"),
    LEFT_BRACE(Patterns.LEFT_BRACE, "left_brace"),
    RIGHT_BRACE(Patterns.RIGHT_BRACE, "right_brace"),
    LEFT_PARENTHESIS(Patterns.LEFT_PARENTHESIS, "left_parenthesis"),
    RIGHT_PARENTHESIS(Patterns.RIGHT_PARENTHESIS, "right_parenthesis"),
    EQUALITY(Patterns.EQUALITY, "equality"),
    COMA(Patterns.COMA, "coma"),
    SEMICOLON(Patterns.SEMICOLON, "semicolon");

    private String value;
    private String name;

    ActionPatterns(String value, String name) {
        this.value = value;
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public static ActionPatterns getFromName(String name) {
        for (ActionPatterns pattern : ActionPatterns.values()) {
            if (pattern.name.equals(name)) {
                return pattern;
            }
        }
        return null;
    }
}
