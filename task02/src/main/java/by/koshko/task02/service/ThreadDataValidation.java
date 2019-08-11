package by.koshko.task02.service;

/**
 * Contains method for validate arguments used for thread creation.
 */
public final class ThreadDataValidation {
    /**
     * Index of an unique number for thread in array.
     */
    private static final int THREAD_NUMBER = 0;
    /**
     * Index of a name for thread in array.
     */
    private static final int THREAD_NAME = 1;
    /**
     * Regex for number validation.
     */
    private static final String NUMBER_PATTERN = "[0-9]{1,9}";
    private ThreadDataValidation() {
    }

    /**
     * Validates arguments in given string array.
     * @param args arguments to be validated.
     * @return {@code true} if all arguments is valid, {@code false} otherwise.
     */
    public static boolean validate(final String[] args) {
        return args.length == 2
                && args[THREAD_NUMBER].matches(NUMBER_PATTERN)
                && args[THREAD_NAME].length() >= 1;
    }
}
