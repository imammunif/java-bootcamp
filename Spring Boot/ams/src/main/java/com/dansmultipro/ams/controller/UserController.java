package com.dansmultipro.ams.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserController {

    @GetMapping("welcome")
    public String hello() {
        return "Hello";
    }

    @GetMapping("welcome2")
    public String hello2() {
        return "Hello 2";
    }
}
