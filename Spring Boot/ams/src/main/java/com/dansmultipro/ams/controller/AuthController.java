package com.dansmultipro.ams.controller;

import com.dansmultipro.ams.dto.user.LoginRequestDto;
import com.dansmultipro.ams.dto.user.LoginResponseDto;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @PostMapping("login")
    public ResponseEntity<LoginResponseDto> loginUser(@RequestBody @Valid LoginRequestDto login) {
        return null;
    }

}
