package org.example.bankingManagementApplication.exceptionHandler;

public class AlreadyExistsExceptionHandler extends RuntimeException{
    public AlreadyExistsExceptionHandler(String message) {
        super(message);
    }

    public AlreadyExistsExceptionHandler(String message, Throwable cause) {
        super(message, cause);
    }
}
