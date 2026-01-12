package com.dansmultipro.ams.controller;

import com.dansmultipro.ams.dto.CreateResponse;
import com.dansmultipro.ams.dto.DeleteResponse;
import com.dansmultipro.ams.dto.UpdateResponse;
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
    public CreateResponse createUser(@RequestBody UserRequestDto user) {
        return null;
    }

    @PutMapping("/{id}")
    public UpdateResponse updateUser(@PathVariable String id, @RequestBody UserRequestDto user) {
        return null;
    }

    @DeleteMapping("/{id}")
    public DeleteResponse deleteUser(@PathVariable String id) {
        return null;
    }

    // TODO REGISTER API

    // TODO LOGIN API

    @PatchMapping("/change-password")
    public UpdateResponse changePassword(@RequestBody ResetPassRequestDto reset) {
        return null;
    }

}
