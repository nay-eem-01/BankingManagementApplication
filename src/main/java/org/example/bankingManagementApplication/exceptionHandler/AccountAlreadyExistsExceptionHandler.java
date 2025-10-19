package org.example.bankingManagementApplication.exceptionHandler;

public class AccountAlreadyExistsExceptionHandler extends RuntimeException{
    public AccountAlreadyExistsExceptionHandler(String message) {
        super(message);
    }

    public AccountAlreadyExistsExceptionHandler(String message, Throwable cause) {
        super(message, cause);
    }
}
