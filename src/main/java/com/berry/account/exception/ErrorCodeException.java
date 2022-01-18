package com.berry.account.exception;

import lombok.Getter;

@Getter
public class ErrorCodeException extends RuntimeException {

    private final int code;
    private final String message;

    public ErrorCodeException(ErrorCode errorCode) {
        this.code = errorCode.getErrorCode();
        this.message = errorCode.getErrorMessage();
    }

}
