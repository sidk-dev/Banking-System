package utils;

// final prevents inheritance
public final class Constants {
    public static final int CHOICES = 7;

    // Private constructor prevents instantiation
    private Constants() {
        // Throwing error to prevent inner classes from calling the constructor.
        throw new AssertionError("Cannot instantiate this class");
    }
}
