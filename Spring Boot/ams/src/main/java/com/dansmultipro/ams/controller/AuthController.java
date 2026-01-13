package com.dansmultipro.ams.controller;

import com.dansmultipro.ams.dto.user.LoginRequestDto;
import com.dansmultipro.ams.dto.user.LoginResponseDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthController {

    @PostMapping("login")
    public LoginResponseDto loginUser(@RequestBody LoginRequestDto login) {
        return null;
    }

}
