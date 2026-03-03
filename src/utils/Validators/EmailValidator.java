package utils.Validators;

import java.util.ArrayList;
import java.util.List;

/**
 * This utility class helps in validating email.
 */
public final class EmailValidator {
    public static final int MAX_LENGTH = 256;

    private EmailValidator() {}

    public static List<String> validate(String email) {
        List<String> errors = new ArrayList<>();

        if (email == null || email.trim().isEmpty()) {
            errors.add("Email can not be empty.");
            return errors;
        }

        email = email.trim();

        boolean isValid = email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");
        if (!isValid) {
            errors.add("Please enter a valid email address.");
        }

        if (email.length() > MAX_LENGTH) {
            errors.add("Email should be less than " + MAX_LENGTH + " characters.");
        }

        if (errors.isEmpty()) return null;
        return errors;
    }
}
