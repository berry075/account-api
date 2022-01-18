package com.berry.account.sign;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SignInUser {
    private String signId;
    private String password;
}
