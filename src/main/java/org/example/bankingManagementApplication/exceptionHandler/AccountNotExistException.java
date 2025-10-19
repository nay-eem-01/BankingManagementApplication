package org.example.bankingManagementApplication.exceptionHandler;

public class AccountNotExistException extends RuntimeException {
    public AccountNotExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public AccountNotExistException(String message) {
        super(message);
    }
}
