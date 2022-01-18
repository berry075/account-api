package com.berry.account.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    USER_NOT_FOUND_BY_TEL(403, "미등록 사용자입니다."),
    USER_NOT_FOUND_BY_EMAIL(403, "미등록 사용자입니다."),
    USER_NOT_FOUND_BY_ID(403, "미등록 사용자입니다."),
    USER_INVALID_PASSWORD(403, "비밀번호가 틀렸습니다.")
    ;

    private int errorCode;
    private String errorMessage;
}
