package com.univille.openink.infra.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.List;

@RestControllerAdvice
public class GlobalHandlerException {

    // ── NotFoundException → 404 ──────────────────────────────────────────────
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(NotFoundException ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage()));
    }

    // ── UnauthorizedException → 401 ──────────────────────────────────────────
    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ErrorResponse> handleUnauthorized(UnauthorizedException ex) {
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(new ErrorResponse(HttpStatus.UNAUTHORIZED.value(), ex.getMessage()));
    }

    // ── TokenJWTException → 401 ──────────────────────────────────────────────
    @ExceptionHandler(TokenJWTException.class)
    public ResponseEntity<ErrorResponse> handleTokenJWT(TokenJWTException ex) {
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(new ErrorResponse(HttpStatus.UNAUTHORIZED.value(), ex.getMessage()));
    }

    // ── SecurityConfigurationException → 500 ────────────────────────────────
    @ExceptionHandler(SecurityConfigurationException.class)
    public ResponseEntity<ErrorResponse> handleSecurityConfig(SecurityConfigurationException ex) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage()));
    }

    // ── MethodArgumentNotValidException → 422 ────────────────────────────────
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorResponse> handleValidation(MethodArgumentNotValidException ex) {
        List<FieldError> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(fe -> new FieldError(fe.getField(), fe.getDefaultMessage()))
                .toList();

        return ResponseEntity
                .status(HttpStatus.UNPROCESSABLE_ENTITY)
                .body(new ValidationErrorResponse(HttpStatus.UNPROCESSABLE_ENTITY.value(), "Validation failed", errors));
    }

    // ── Response bodies ───────────────────────────────────────────────────────

    public record ErrorResponse(
            int status,
            String message,
            LocalDateTime timestamp
    ) {
        public ErrorResponse(int status, String message) {
            this(status, message, LocalDateTime.now());
        }
    }

    public record FieldError(String field, String message) {}

    public record ValidationErrorResponse(
            int status,
            String message,
            List<FieldError> errors,
            LocalDateTime timestamp
    ) {
        public ValidationErrorResponse(int status, String message, List<FieldError> errors) {
            this(status, message, errors, LocalDateTime.now());
        }
    }
}