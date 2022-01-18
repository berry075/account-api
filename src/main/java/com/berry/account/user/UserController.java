package com.berry.account.user;

import com.berry.account.response.SuccessCode;
import com.berry.account.response.SuccessResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(
    path = "/users"
)
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    @PostMapping
    @Operation(summary = "회원가입", description = "이메일 또는 전화번호가 이미 등록되어있으면 안됩니다.")
    public ResponseEntity<SuccessResponse> register(@RequestBody User user) {
        return ResponseEntity.ok(new SuccessResponse(SuccessCode.CREATE_USER, userService.create(user)));

    }

    @GetMapping("/{id}")
    @Operation(summary = "내정보보기", description = "id 또는 이메일 또는 전화번호로 user 레코드를 조회합니다.")
    public ResponseEntity<SuccessResponse> read(@PathVariable String id) {
        return ResponseEntity.ok(new SuccessResponse(SuccessCode.SELECT_USER, userService.find(id)));
    }

    @PatchMapping("/password_reset")
    @Operation(summary = "비밀번호변경", description = "id 또는 이메일 또는 전화번호로 비밀번호를 변경합니다.")
    public ResponseEntity<SuccessResponse> modifyPassword(@RequestBody ResetPassword resetPassword) {
        userService.modifyPasswordById(resetPassword);
        return ResponseEntity.ok(new SuccessResponse(SuccessCode.PASSWORD_RESET));
    }
}
