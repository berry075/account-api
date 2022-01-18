package com.berry.account.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorAdvisor {

    @ExceptionHandler(ErrorCodeException.class)
    public ResponseEntity<ErrorResponse> notFoundException(ErrorCodeException e) {
        ErrorResponse errorResponse = ErrorResponse.builder()
            .code(e.getCode())
            .message(e.getMessage())
            .build();

        return ResponseEntity.status(HttpStatus.resolve(errorResponse.getCode())).body(errorResponse);
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity exception(Exception e){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("");
    }
}
