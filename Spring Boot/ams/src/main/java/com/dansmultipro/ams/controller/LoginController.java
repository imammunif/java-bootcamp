package com.dansmultipro.ams.controller;

import com.dansmultipro.ams.dto.user.LoginRequestDto;
import com.dansmultipro.ams.dto.user.LoginResponseDto;
import com.dansmultipro.ams.service.UserService;
import com.dansmultipro.ams.util.JwtUtil;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@RestController
public class LoginController {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;

    public LoginController(AuthenticationManager authenticationManager, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
    }

    @PostMapping("login")

    public ResponseEntity<LoginResponseDto> loginUser(@RequestBody @Valid LoginRequestDto loginRequestDto) {
        var auth = new UsernamePasswordAuthenticationToken(loginRequestDto.getEmail(), loginRequestDto.getPassword());
        authenticationManager.authenticate(auth);

        var user = userService.findByEmail(loginRequestDto.getEmail());
        var token = JwtUtil.generateToken(user.getId().toString(), Timestamp.valueOf(LocalDateTime.now().plusHours(2)));
        return new ResponseEntity<>(new LoginResponseDto(
                user.getEmployee().getFullName(), user.getRole().getCode(), token
        ), HttpStatus.OK);
    }

}
