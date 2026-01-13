package com.dansmultipro.ams.controller;

import com.dansmultipro.ams.dto.CreateResponseDto;
import com.dansmultipro.ams.dto.DeleteResponseDto;
import com.dansmultipro.ams.dto.UpdateResponseDto;
import com.dansmultipro.ams.dto.user.ResetPassRequestDto;
import com.dansmultipro.ams.dto.user.UserRequestDto;
import com.dansmultipro.ams.dto.user.UserResponseDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {

    @GetMapping
    public List<UserResponseDto> getAllUsers() {
        return null;
    }

    @GetMapping("/{id}")
    public UserResponseDto getUserById(@PathVariable String id) {
        return null;
    }

    @PostMapping
    public CreateResponseDto createUser(@RequestBody UserRequestDto user) {
        return null;
    }

    @PutMapping("/{id}")
    public UpdateResponseDto updateUser(@PathVariable String id, @RequestBody UserRequestDto user) {
        return null;
    }

    @DeleteMapping("/{id}")
    public DeleteResponseDto deleteUser(@PathVariable String id) {
        return null;
    }

    @PatchMapping("/change-password")
    public UpdateResponseDto changePassword(@RequestBody ResetPassRequestDto reset) {
        return null;
    }

}
