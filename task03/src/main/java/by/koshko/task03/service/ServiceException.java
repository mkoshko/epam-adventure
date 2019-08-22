package by.koshko.task03.service;

/**
 * Throws by classes from service layer.
 */
public class ServiceException extends Exception {
    /**
     * Construct exception with given message.
     * @param message message to be stored in exception. Can be obtained by
     *                calling {@link #getMessage()} method.
     */
    public ServiceException(final String message) {
        super(message);
    }
}
