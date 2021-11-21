package com.company;

public class Patterns {
    public static final String DOTE = "\\.";

    public static final String EQUALITY = "\\=";

    public static final String LEFT_BRACE = "\\{";

    public static final String RIGHT_BRACE = "\\}";

    public static final String LEFT_PARENTHESIS = "\\(";

    public static final String RIGHT_PARENTHESIS = "\\)";

    public static final String COMA = "\\,";

    public static final String SEMICOLON = "\\;";

    public static final String DECIMAL_LITERAL = "\b([0-9]*[.]?[0-9]+([eE][-+]?[0-9]+)?)";

    public static final String BOOL_LITERAL = "(?<!\\w)(false|true)(?!\\w)";

    public static final String KEY_DELIMITER = "[(){};,]";

    public static final String ALL_DELIMITERS = " [(){};,]\n\r";

    public static final String STRING_LITERAL = "\"(?:\\\\\"|[^\"])*?\"";

    public static final String COMMENT = "(\\/\\/(.)*\\b)+|\\/\\*((.)*\\n)*\\*\\/";

    public static final String KEYWORD = "if|int|long|float|double|char|for|else|private" +
            "|public|static|void|volatile|import|package|this|instanceof|new|class|try" +
            "|catch|while|do|return";

    public static final String IDENTIFIER = "(?:\\\\\"|[^\"\\.])*?";

    public static final String CHAR = "'(\\\\['\"tvrnafb\\\\]|[^'\\\\])'";

    public static final String OPERATORS = "<(?!=)|>(?!=)|<=|>=|!=|(?<![<>!=+-/%&^|])=|==|!(?!=)|\\+(?!\\+)|-(?!-)|\\*|/|%|\\+\\+|--|&&|(?!&)&|~|<<|>>(?!>)|>>>|\\[|]";
}
