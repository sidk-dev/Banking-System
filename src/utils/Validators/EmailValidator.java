package utils.Validators;

/**
 * This utility class helps in validating email.
 */
public final class EmailValidator {
    public static final int MAX_LENGTH = 256;

    private EmailValidator() {}

    public static String validate(String email) {
        StringBuilder errors = new StringBuilder();

        if (email == null || email.trim().isEmpty()) {
            errors.append("Email can not be empty.\n");
            return errors.toString();
        }

        email = email.trim();

        boolean isValid = email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");
        if (!isValid) {
            errors.append("Please enter a valid email address.\n");
        }

        if (email.length() > MAX_LENGTH) {
            errors.append("Email should be less than " + MAX_LENGTH + " characters\n");
        }

        if (errors.isEmpty()) return null;
        return errors.toString();
    }
}
