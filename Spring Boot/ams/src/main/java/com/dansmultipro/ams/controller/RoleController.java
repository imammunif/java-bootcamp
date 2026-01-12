package com.dansmultipro.ams.controller;

import com.dansmultipro.ams.dto.user.UserRoleResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("roles")
public class RoleController {

    @GetMapping
    public List<UserRoleResponseDto> getAllRoles() {
        return null;
    }

    @GetMapping("{id}")
    public UserRoleResponseDto getRoleById(@PathVariable String id) {
        return null;
    }

}
