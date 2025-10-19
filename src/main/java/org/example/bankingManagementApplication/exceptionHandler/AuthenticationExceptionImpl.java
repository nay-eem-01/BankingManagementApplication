package org.example.bankingManagementApplication.exceptionHandler;

import org.springframework.security.core.AuthenticationException;

public class AuthenticationExceptionImpl extends AuthenticationException {
    public AuthenticationExceptionImpl(String msg) {
        super(msg);
    }
}
