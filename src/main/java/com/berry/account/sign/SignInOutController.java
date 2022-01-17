package com.berry.account.sign;

import com.berry.account.user.User;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class SignInOutController {

    private final SignInOutService signInOutService;

    @PostMapping("/sign-in")
    public boolean signIn(@RequestBody Map<String, String> params) {
        String signId = params.get("signId");
        String password = params.get("password");
        User user = signInOutService.signInBySignId(signId, password);
        if (user == null) return false;
        return true;
    }
}
