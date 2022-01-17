package com.berry.account.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/users")
@Slf4j
public class UserController {

    @PostMapping
    public void register() {

    }

    @GetMapping("/{id}")
    public void read() {

    }
}
