package com.example.demo.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ApiException extends RuntimeException {
    private final String code;
    private final HttpStatus status;
    private final String errorClass;

    public ApiException(String code, HttpStatus status, String message) {
        super(message);
        this.code = code;
        this.status = status;
        this.errorClass = this.getClass().getSimpleName();
    }

    public ApiException(String code, String message) {
        super(message);
        this.code = code;
        this.status = HttpStatus.BAD_REQUEST;
        this.errorClass = this.getClass().getSimpleName();
    }

    public ApiException(Throwable ex) {
        super(ex.getMessage(), ex);
        this.code = "UNKNOWN_ERROR";
        this.status = HttpStatus.INTERNAL_SERVER_ERROR;
        this.errorClass = ex.getClass().getSimpleName();
    }
}
