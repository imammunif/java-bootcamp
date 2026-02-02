package com.dansmultipro.ops.controller;

import com.dansmultipro.ops.dto.CommonResponseDto;
import com.dansmultipro.ops.dto.CreateResponseDto;
import com.dansmultipro.ops.dto.DeleteResponseDto;
import com.dansmultipro.ops.dto.UpdateResponseDto;
import com.dansmultipro.ops.dto.user.CreateUserCustomerRequestDto;
import com.dansmultipro.ops.dto.user.CreateUserGatewayRequestDto;
import com.dansmultipro.ops.dto.user.UpdateUserRequestDto;
import com.dansmultipro.ops.dto.user.UserResponseDto;
import com.dansmultipro.ops.service.UserService;
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
    public ResponseEntity<List<UserResponseDto>> getAll() {
        List<UserResponseDto> res = userService.getAll();
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<UserResponseDto> getById(
            @PathVariable String id
    ) {
        UserResponseDto res = userService.getById(id);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<CreateResponseDto> createUserCustomer(
            @RequestBody @Valid CreateUserCustomerRequestDto data
    ) {
        CreateResponseDto res = userService.createUserCustomer(data);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @PostMapping("/gateways")
    public ResponseEntity<CreateResponseDto> createGatewayAdmin(
            @RequestBody @Valid CreateUserGatewayRequestDto data
    ) {
        CreateResponseDto res = userService.createUserGateway(data);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @GetMapping("{email}/activate/{code}")
    public ResponseEntity<CommonResponseDto> activateUserCustomer(
            @PathVariable String email,
            @PathVariable String code
    ) {
        CommonResponseDto res = userService.activateUserCustomer(email, code);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<UpdateResponseDto> update(
            @PathVariable String id,
            @RequestBody @Valid UpdateUserRequestDto data
    ) {
        UpdateResponseDto res = userService.update(id, data);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<DeleteResponseDto> delete(
            @PathVariable String id
    ) {
        DeleteResponseDto res = userService.deleteById(id);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

}
