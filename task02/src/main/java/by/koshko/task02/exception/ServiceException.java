package by.koshko.task02.exception;

public class ServiceException extends Exception {
    /**
     * Constructs a new by.koshko.task02.exception with the specified detail message.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    public ServiceException(final String message) {
        super(message);
    }
}
