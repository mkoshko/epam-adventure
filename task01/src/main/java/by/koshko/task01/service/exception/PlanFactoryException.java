package by.koshko.task01.service.exception;

public class PlanFactoryException extends Exception {

    public PlanFactoryException() {
    }

    public PlanFactoryException(final String message) {
        super(message);
    }

    public PlanFactoryException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public PlanFactoryException(final Throwable cause) {
        super(cause);
    }
}
