package com.berry.account.user;

import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    @PostMapping
    public User register(@RequestBody User user) {
        return userService.create(user);
    }

    @GetMapping("/{id}")
    public User read(@PathVariable String id) {
        return userService.find(id);
    }

    @PatchMapping("/{id}/password_reset")
    public void modifyPassword(@PathVariable String id,@RequestBody Map<String, String> params) {
        String password = params.get("password");
        String newPassword = params.get("newPassword");
        userService.modifyPasswordById(id, password, newPassword);
    }
}
