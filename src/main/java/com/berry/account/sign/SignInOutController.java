package com.berry.account.sign;

import com.berry.account.response.SuccessCode;
import com.berry.account.response.SuccessResponse;
import com.berry.account.user.User;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@RequiredArgsConstructor
@Slf4j
public class SignInOutController {

    private final SignInOutService signInOutService;

    @PostMapping("/sign-in")
    @Operation(summary = "로그인", description = "이메일 또는 전화번호로 로그인")
    public ResponseEntity<SuccessResponse> signIn(@RequestBody SignInUser signInUser) {
        User user = signInOutService.signInBySignId(signInUser);
        return ResponseEntity.ok(new SuccessResponse(SuccessCode.LOGIN));
    }

    @PostMapping("/sign-out")
    @Operation(summary = "로그아웃", description = "이메일 또는 전화번호로 로그아웃")
    public ResponseEntity<SuccessResponse> signOut(@RequestBody SignInUser signInUser) {
        return ResponseEntity.ok(new SuccessResponse(SuccessCode.LOGOUT));
    }
}
