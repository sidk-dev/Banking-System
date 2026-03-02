package utils.Validators;

public final class FullNameValidator {
    public static final int MIN_LENGTH = 2;
    public static final int MAX_LENGTH = 54;

    private FullNameValidator() {}

    public static String validate(String fullName) {
        StringBuilder errors = new StringBuilder();

        if (fullName == null || fullName.isEmpty()) {
            errors.append("Full Name can not be empty.\n");
            return errors.toString();
        }

        if (fullName.length() < MIN_LENGTH) {
            errors.append("Full Name must be at least " + MIN_LENGTH + " characters\n");
        } else if (fullName.length() > MAX_LENGTH) {
            errors.append("Full Name must be less than " + MAX_LENGTH + " characters\n");
        }

        if (errors.isEmpty()) return null;
        return errors.toString();
    }
}
