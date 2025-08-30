package com.example.demo.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);


    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String, Object>> handleRuntime(RuntimeException ex) {
        ApiException apiEx;
        if (ex instanceof ApiException) {
            apiEx = (ApiException) ex;

        } else {
            apiEx = new ApiException(ex);
        }

        if (apiEx.getStatus() == HttpStatus.INTERNAL_SERVER_ERROR) {
            logger.error(apiEx.getMessage(), apiEx);
        } else {
            logger.error(apiEx.getMessage());
        }

        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("error", apiEx.getClass().getSimpleName());
        body.put("message", apiEx.getMessage());
        body.put("errorCode", apiEx.getCode());
        body.put("status", apiEx.getStatus());

        return new ResponseEntity<>(body, apiEx.getStatus());
    }
}