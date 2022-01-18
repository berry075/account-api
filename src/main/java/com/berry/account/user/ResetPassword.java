package com.berry.account.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Schema(description = "비밀번호 초기화를 위한 객체")
@Getter
@AllArgsConstructor
public class ResetPassword {

    @Schema(description = "id 또는 이메일 또는 전화번호", nullable = false)
    private String signId;
    @Schema(description = "기존 비밀번호 (plain text)", nullable = false)
    private String password;
    @Schema(description = "신규 비밀번호", nullable = false)
    private String newPassword;
}
