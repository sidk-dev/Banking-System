package utils;

public final class Styles {
    // ANSI escape code for Red text
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";

    // ANSI escape code to reset the text color
    public static final String ANSI_RESET_COLOR = "\u001B[0m";

    private Styles() {
        throw new AssertionError("Cannot instantiate this class");
    }
}
