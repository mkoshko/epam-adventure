package by.koshko.task01.exception;

public class IllegalPlanParametersExceptions extends Exception {

    public IllegalPlanParametersExceptions() {
        super();
    }

    public IllegalPlanParametersExceptions(final String message) {
        super(message);
    }

    public IllegalPlanParametersExceptions(final String message, final Throwable cause) {
        super(message, cause);
    }

    public IllegalPlanParametersExceptions(final Throwable cause) {
        super(cause);
    }
}
