package org.example.firstproject.exceptionHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.firstproject.model.response.HttpResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;
import java.io.Serializable;

public class AuthenticationExceptionHandler extends AuthenticationException{


    public AuthenticationExceptionHandler(String msg) {
        super(msg);
    }

    public AuthenticationExceptionHandler(String msg, Throwable cause) {
        super(msg, cause);
    }
}
