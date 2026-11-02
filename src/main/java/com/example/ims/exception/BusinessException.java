package com.example.ims.exception;

import com.example.ims.exception.errorcode.ErrorCode;

import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {
    private final Integer code;
    private final ErrorCode errorCode;

    public BusinessException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.code = errorCode.getHttpStatus().value();
        this.errorCode = errorCode;
    }
}
