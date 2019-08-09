package service;

public class ThreadDataValidation {
    private static final int THREAD_NUMBER = 0;
    private static final int THREAD_NAME = 1;
    private static final String NUMBER_PATTERN = "[0-9]{1,9}";
    private ThreadDataValidation() {
    }
    public static boolean validate(final String[] args) {
        return args[THREAD_NUMBER].matches(NUMBER_PATTERN)
                && args[THREAD_NAME].length() >= 1;
    }
}
