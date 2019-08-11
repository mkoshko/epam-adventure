package by.koshko.task02.exception;

public class DaoException extends Exception {
    /**
     * Constructs an exception with the specified detail message.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    public DaoException(final String message) {
        super(message);
    }
}
