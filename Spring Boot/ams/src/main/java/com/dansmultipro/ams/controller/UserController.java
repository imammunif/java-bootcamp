package com.dansmultipro.ams.controller;

import com.dansmultipro.ams.dto.CreateResponse;
import com.dansmultipro.ams.dto.DeleteResponse;
import com.dansmultipro.ams.dto.UpdateResponse;
import com.dansmultipro.ams.dto.user.ResetPassRequestDto;
import com.dansmultipro.ams.dto.user.UserRequest;
import com.dansmultipro.ams.dto.user.UserResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {

    @GetMapping
    public List<UserResponse> getAllUsers() {
        return null;
    }

    @GetMapping("/{id}")
    public UserResponse getUserById(@PathVariable String id) {
        return null;
    }

    @PostMapping
    public CreateResponse createUser(@RequestBody UserRequest user) {
        return null;
    }

    @PutMapping("/{id}")
    public UpdateResponse updateUser(@PathVariable String id, @RequestBody UserRequest user) {
        return null;
    }

    @DeleteMapping("/{id}")
    public DeleteResponse deleteUser(@PathVariable String id) {
        return null;
    }

    @PatchMapping("/change-password")
    public UpdateResponse changePassword(@RequestBody ResetPassRequestDto reset) {
        return null;
    }

}
