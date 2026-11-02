package com.example.ims.exception.errorcode;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum TestingErrorCode implements ErrorCode {
    NOT_FOUND("Not Found", HttpStatus.NOT_FOUND);

    private final String message;
    private final HttpStatus httpStatus;

    @Override
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
