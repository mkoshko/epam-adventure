package by.koshko.task01.service.validation;

public final class Validator {
    private Validator() {
    }

    /**
     * Regex pattern for names.
     */
    private static final String NAME_PATTERN = "^[a-zA-z0-9 ]{1,15}";
    /**
     * Regex pattern for double values.
     */
    private static final String DOUBLE_PATTERN = "^\\d+(\\.\\d+)?";
    /**
     * Regex pattern for integer values.
     */
    private static final String INTEGER_PATTERN = "^\\d{1,10}";
    /**
     * Regex pattern for long values.
     */
    private static final String LONG_PATTERN = "^\\d{1,19}";

    /**
     * Validate if string value can be parsed to {@code Double}.
     *
     * @param value Value to be parsed.
     * @return {@code true} if string can be parsed to {@code Double}.
     */
    public static boolean validateDouble(final String value) {
        return value != null && value.matches(DOUBLE_PATTERN);
    }
    /**
     * Validate if string value can be parsed to {@code Long}.
     *
     * @param value Value to be parsed.
     * @return {@code true} if string can be parsed to {@code Long}.
     */
    public static boolean validateLong(final String value) {
        return value != null && value.matches(LONG_PATTERN);
    }
}
