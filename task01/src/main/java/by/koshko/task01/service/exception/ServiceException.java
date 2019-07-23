package by.koshko.task01.service.exception;

public class ServiceException extends Exception {
    /**
     * Constructs a new exception with the specified detail message.
     *
     * @param message the detail message.
     */
    public ServiceException(final String message) {
        super(message);
    }
}
