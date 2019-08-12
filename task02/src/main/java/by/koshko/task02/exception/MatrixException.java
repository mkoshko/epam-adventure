package by.koshko.task02.exception;

/**
 * Thrown while trying to create {@code Matrix} with invalid size parameters,
 * or while trying to set an element with indexes that out of range.
 */
public class MatrixException extends Exception {
    /**
     * Constructs a new exception with the specified detail message.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    public MatrixException(final String message) {
        super(message);
    }

}
