package by.koshko.task01.service.exception;

public class WrongPlanTypeException extends PlanFactoryException {

    public WrongPlanTypeException() {
        super();
    }

    public WrongPlanTypeException(final String message) {
        super(message);
    }

    public WrongPlanTypeException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public WrongPlanTypeException(final Throwable cause) {
        super(cause);
    }
}
