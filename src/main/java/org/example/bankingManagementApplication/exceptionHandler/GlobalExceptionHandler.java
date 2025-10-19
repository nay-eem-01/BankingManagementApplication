package org.example.bankingManagementApplication.exceptionHandler;

import jakarta.servlet.http.HttpServletRequest;
import org.example.bankingManagementApplication.model.response.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<HttpResponse> handleUserAlreadyExists(UserAlreadyExistsException ex, HttpServletRequest request) {

        return HttpResponse.getResponseEntityForError(
                HttpStatus.CONFLICT,
                ex.getMessage(),
                null);
    }

    @ExceptionHandler(AccountAlreadyExistsExceptionHandler.class)
    public ResponseEntity<HttpResponse> handleAccountAlreadyExists(AccountAlreadyExistsExceptionHandler ex, HttpServletRequest request) {

        return HttpResponse.getResponseEntityForError(
                HttpStatus.CONFLICT,
                ex.getMessage(),
                null);
    }

    @ExceptionHandler(AccountNotExistException.class)
    public ResponseEntity<HttpResponse> handleAccountNotExists(AccountNotExistException ex, HttpServletRequest request) {

        return HttpResponse.getResponseEntityForError(
                HttpStatus.NOT_FOUND,
                ex.getMessage(),
                null);
    }


    @ExceptionHandler(AuthenticationExceptionImpl.class)
    public ResponseEntity<HttpResponse> handleAuthenticationException(AuthenticationException ex, HttpServletRequest request) {

        return HttpResponse.getResponseEntityForError(
                HttpStatus.UNAUTHORIZED,
                ex.getMessage(),
                null);
    }


    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<HttpResponse> handleResourceNotFoundException(ResourceNotFoundException ex, HttpServletRequest request) {

        return HttpResponse.getResponseEntityForError(
                HttpStatus.NOT_FOUND,
                ex.getMessage(),
                null);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<HttpResponse> handleUserNotFound(UsernameNotFoundException ex, HttpServletRequest request) {

        return HttpResponse.getResponseEntityForError(
                HttpStatus.NOT_FOUND,
                ex.getMessage(),
                null);
    }

    @ExceptionHandler(TransactionExceptionHandler.class)
    public ResponseEntity<HttpResponse> handleTransactionException(TransactionExceptionHandler ex, HttpServletRequest request) {

        return HttpResponse.getResponseEntityForError(
                HttpStatus.BAD_REQUEST,
                ex.getMessage(),
                null);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<HttpResponse> handleBadCredentials(
            BadCredentialsException ex, HttpServletRequest request) {

        return HttpResponse.getResponseEntityForError(
                HttpStatus.UNAUTHORIZED,
                "Invalid password",
                null);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<HttpResponse> handleAccessDenied(
            AccessDeniedException ex, HttpServletRequest request) {

        return HttpResponse.getResponseEntityForError(
                HttpStatus.UNAUTHORIZED,
                "You do not have permission",
                ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<HttpResponse> handleValidationErrors(
            MethodArgumentNotValidException ex, HttpServletRequest request) {

        Map<String, String> fieldErrors = new HashMap<>();
        ex.getBindingResult().getFieldErrors()
                .forEach(error -> fieldErrors.put(error.getField(), error.getDefaultMessage()));

        return HttpResponse.getResponseEntityForError(
                HttpStatus.BAD_REQUEST,
                ex.getMessage(),
                fieldErrors
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<HttpResponse> handleGenericException(Exception ex, HttpServletRequest request) {
        logger.error("Unexpected error occurred", ex);
        return HttpResponse.getResponseEntityForError(
                HttpStatus.INTERNAL_SERVER_ERROR,
                ex.getMessage(),
                null);
    }


}
