package utils.Validators;

import java.util.ArrayList;
import java.util.List;

/**
 * This utility class helps in validating user's fullName.
 */
public final class FullNameValidator {
    public static final int MIN_LENGTH = 2;
    public static final int MAX_LENGTH = 54;

    private FullNameValidator() {}

    public static List<String> validate(String fullName) {
        List<String> errors = new ArrayList<>();

        if (fullName == null || fullName.trim().isEmpty()) {
            errors.add("Full Name can not be empty.");
            return errors;
        }

        fullName = fullName.trim();

        if (fullName.length() < MIN_LENGTH) {
            errors.add("Full Name must be at least " + MIN_LENGTH + " characters.");
        } else if (fullName.length() > MAX_LENGTH) {
            errors.add("Full Name must be less than " + MAX_LENGTH + " characters.");
        }

        if (errors.isEmpty()) return null;
        return errors;
    }
}
