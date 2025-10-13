package org.example.firstproject.exceptionHandler;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class UserAlreadyExistsException extends RuntimeException{
    public UserAlreadyExistsException(String message) {
        super(message);
    }

    public UserAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
