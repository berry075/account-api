package com.berry.account.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SuccessCode {
    LOGIN(200, "로그인 성공"),
    LOGOUT(200, "로그아웃 완료"),
    CREATE_USER(200, "회원가입완료"),
    SELECT_USER(200, "내 정보 조회 완료"),
    PASSWORD_RESET(200, "비밀번호 초기화 완전"),
    COMPLETED_SMS_AUTH(200, "전화번호 인증 완료")
    ;

    private int successCode;
    private String successMessage;
}
