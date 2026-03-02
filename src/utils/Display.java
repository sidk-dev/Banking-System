package utils;

public final class Display {
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";   // Magenta
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_RED = "\u001B[31m";         // ANSI escape code for Red text
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RESET_COLOR = "\u001B[0m";  // ANSI escape code to reset the text color

    private Display() {}

    public static void menu(String msg) {
        System.out.printf("%s%s%s\n", ANSI_YELLOW, msg, ANSI_RESET_COLOR);
    }

    public static void input(String msg) {
        System.out.printf("%s%s%s", ANSI_CYAN, msg, ANSI_RESET_COLOR);
    }

    public static void success(String msg) {
        System.out.printf("%s%s%s\n", ANSI_GREEN, msg, ANSI_RESET_COLOR);
    }

    public static void failure(String msg) {
        System.out.printf("%s%s%s\n", ANSI_RED, msg, ANSI_RESET_COLOR);
    }
}
