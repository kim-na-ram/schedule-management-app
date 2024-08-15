package com.bootcamp.schedulemanagementapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ApiException.class)
    protected ResponseEntity<?> handleApiExceptions(ApiException e) {
        return new ResponseEntity<>(e.getMessage(), e.getHttpStatus());
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<?> handleAllExceptions(Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<?> handleValidationExceptions(MethodArgumentNotValidException e) {
        StringBuilder sb = new StringBuilder();
        e.getBindingResult().getAllErrors()
                .forEach(c -> sb.append(c.getDefaultMessage()).append("\n"));
        sb.deleteCharAt(sb.length() - 1);

        return new ResponseEntity<>(sb.toString(), HttpStatus.BAD_REQUEST);
    }
}