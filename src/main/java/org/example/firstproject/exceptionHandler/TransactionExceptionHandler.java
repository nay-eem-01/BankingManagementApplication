package org.example.firstproject.exceptionHandler;

public class TransactionExceptionHandler extends RuntimeException {
    public TransactionExceptionHandler(String message) {
        super(message);
    }

    public TransactionExceptionHandler(String message, Throwable cause) {
        super(message, cause);
    }
}
