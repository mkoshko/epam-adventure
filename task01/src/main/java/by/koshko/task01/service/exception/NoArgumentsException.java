package by.koshko.task01.service.exception;

public class NoArgumentsException extends PlanFactoryException {

    public NoArgumentsException() {
        super();
    }

    public NoArgumentsException(final String message) {
        super(message);
    }

    public NoArgumentsException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public NoArgumentsException(final Throwable cause) {
        super(cause);
    }
}
