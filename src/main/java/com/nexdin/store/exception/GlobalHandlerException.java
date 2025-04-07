package com.nexdin.store.exception;

import com.nexdin.store.payload.Error;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalHandlerException {
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Error<?>> handleIllegalArgumentException(IllegalArgumentException exception) {
        return ResponseEntity.badRequest().body(new Error<>(
                400,
                LocalDateTime.now(),
                exception.getMessage(),
                null
        ));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Error<?>> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        Map<String, String> errors = new HashMap<>();
        exception.getBindingResult().getFieldErrors().forEach(error -> {
            errors.put(error.getField(), exception.getMessage());
        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Error<>(
                400,
                LocalDateTime.now(),
                exception.getMessage(),
                errors
        ));
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Error<?>> handleResourceNotFoundException(ResourceNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Error<>(
                404,
                LocalDateTime.now(),
                exception.getMessage(),
                null
        ));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Error<?>> handleException(Exception exception) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Error<>(
                500,
                LocalDateTime.now(),
                exception.getMessage(),
                null
        ));
    }
}
