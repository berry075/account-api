package com.berry.account.auth;

import com.berry.account.response.SuccessCode;
import com.berry.account.response.SuccessResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(
    path = "/auth"
)
@Slf4j
public class AuthController {

    @PostMapping
    public ResponseEntity<SuccessResponse> authSMS() {
        return ResponseEntity.ok(new SuccessResponse(SuccessCode.COMPLETED_SMS_AUTH));
    }
}
