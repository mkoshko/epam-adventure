package by.koshko.task01.exception;

public class EmptyFileException extends Exception {

    /**
     * Constructs a <code>FileNotFoundException</code> with
     * <code>null</code> as its error detail message.
     */
    public EmptyFileException() {
    }

    /**
     * Constructs a <code>EmptyFileException</code> with the
     * specified detail message. The string <code>s</code> can be
     * retrieved later by the
     * <code>{@link java.lang.Throwable#getMessage}</code>
     * method of class <code>java.lang.Throwable</code>.
     *
     * @param   message   the detail message.
     */
    public EmptyFileException(final String message) {
        super(message);
    }

}
