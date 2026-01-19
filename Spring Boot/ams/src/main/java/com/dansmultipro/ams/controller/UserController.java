package com.dansmultipro.ams.controller;

import com.dansmultipro.ams.dto.CreateResponseDto;
import com.dansmultipro.ams.dto.DeleteResponseDto;
import com.dansmultipro.ams.dto.UpdateResponseDto;
import com.dansmultipro.ams.dto.user.UpdateUserRequestDto;
import com.dansmultipro.ams.dto.user.UserRequestDto;
import com.dansmultipro.ams.dto.user.UserResponseDto;
import com.dansmultipro.ams.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getAllUsers() {
        List<UserResponseDto> res = userService.getAll();
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable String id) {
        UserResponseDto res = userService.getById(id);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CreateResponseDto> createUser(@RequestBody @Valid UserRequestDto user) {
        CreateResponseDto res = userService.insert(user);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<UpdateResponseDto> updateUser(@PathVariable String id, @RequestBody @Valid UpdateUserRequestDto user) {
        UpdateResponseDto res = userService.update(id, user);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<DeleteResponseDto> deleteUser(@PathVariable String id) {
        DeleteResponseDto res = userService.deleteById(id);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

}
