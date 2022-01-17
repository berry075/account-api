package com.berry.account.sign;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class SignInOutController {

    @PostMapping("/sign-in")
    public void signIn() {}
}
