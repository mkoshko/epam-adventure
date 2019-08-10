package exception;

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
