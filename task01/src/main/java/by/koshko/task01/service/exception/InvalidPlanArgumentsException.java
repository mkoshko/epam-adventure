package by.koshko.task01.service.exception;

public class InvalidPlanArgumentsException extends Exception {

    public InvalidPlanArgumentsException() {
    }

    public InvalidPlanArgumentsException(final String message) {
        super(message);
    }

    public InvalidPlanArgumentsException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public InvalidPlanArgumentsException(final Throwable cause) {
        super(cause);
    }
}
