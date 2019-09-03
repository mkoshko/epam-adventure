package by.koshko.task04.service;

public class BankBuilderException extends Exception {
    public BankBuilderException(final String message) {
        super(message);
    }

    public BankBuilderException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
