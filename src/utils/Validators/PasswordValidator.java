package utils.Validators;

import java.util.ArrayList;
import java.util.List;

/**
 * This utility class helps in validating password.
 */
public final class PasswordValidator {
    public static final int MIN_LENGTH = 8;
    public static final int MAX_LENGTH = 128;

    private PasswordValidator() {}

    public static List<String> validate(String password) {
        List<String> errors = new ArrayList<>();

        if (password == null || password.isEmpty()) {
            errors.add("Password can not be empty.\n");
            return errors;
        }

        boolean hasUpper = false;
        boolean hasLower = false;
        boolean hasDigit = false;
        boolean hasSpecial = false;
        boolean hasWhitespace = false;

        for (int i = 0; i < password.length(); i++) {
            char c = password.charAt(i);
            if (Character.isUpperCase(c)) {
                hasUpper = true;
            } else if (Character.isLowerCase(c)) {
                hasLower = true;
            } else if (Character.isDigit(c)) {
                hasDigit = true;
            } else if (Character.isWhitespace(c)) {
                hasWhitespace = true;
            } else {
                hasSpecial = true;
            }
        }

        if (!hasUpper) {
            errors.add("At least one uppercase letter required.");
        }

        if (!hasLower) {
            errors.add("At least one lowercase letter required.");
        }

        if (!hasDigit) {
            errors.add("At least one digit required.");
        }

        if (!hasSpecial) {
            errors.add("At least one special character required.");
        }

        if (hasWhitespace) {
            errors.add("Whitespace is not allowed.");
        }

        if (password.length() < MIN_LENGTH) {
            errors.add("Password must be at least " + MIN_LENGTH + " characters.");
        } else if (password.length() > MAX_LENGTH) {
            errors.add("Password must be less than " + MAX_LENGTH + " characters.");
        }

        if (errors.isEmpty()) return null;
        return errors;
    }
}
