package utils.Validators;

/**
 * This utility class helps in validating password.
 */
public final class PasswordValidator {
    public static final int MIN_LENGTH = 8;
    public static final int MAX_LENGTH = 128;

    private PasswordValidator() {}

    public static String validate(String password) {
        StringBuilder errors = new StringBuilder();

        if (password == null || password.isEmpty()) {
            errors.append("Password can not be empty.\n");
            return errors.toString();
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
            errors.append("At least one uppercase letter required\n");
        }

        if (!hasLower) {
            errors.append("At least one lowercase letter required\n");
        }

        if (!hasDigit) {
            errors.append("At least one digit required\n");
        }

        if (!hasSpecial) {
            errors.append("At least one special character required\n");
        }

        if (hasWhitespace) {
            errors.append("Whitespace is not allowed\n");
        }

        if (password.length() < MIN_LENGTH) {
            errors.append("Password must be at least " + MIN_LENGTH + " characters\n");
        } else if (password.length() > MAX_LENGTH) {
            errors.append("Password must be less than " + MAX_LENGTH + " characters\n");
        }

        if (errors.isEmpty()) return null;
        return errors.toString();
    }
}
