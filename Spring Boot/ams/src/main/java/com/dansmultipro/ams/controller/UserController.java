package com.dansmultipro.ams.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping("/user/welcome")
    public String hello() {
        return "Hello";
    }

    @GetMapping("user/welcome2")
    public String hello2() {
        return "Hello 2";
    }
}
