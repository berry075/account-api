package com.berry.account.sign;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SignInUser {

    @Schema(description = "로그인ID -> email 또는 tel", nullable = false)
    private String signId;
    @Schema(description = "비밀번호", nullable = false)
    private String password;
}
