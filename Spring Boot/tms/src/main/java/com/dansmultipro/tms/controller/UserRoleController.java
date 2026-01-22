package com.dansmultipro.tms.controller;

import com.dansmultipro.tms.dto.userrole.RoleResponseDto;
import com.dansmultipro.tms.service.UserRoleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("roles")
public class UserRoleController {

    private final UserRoleService userRoleService;

    public UserRoleController(UserRoleService userRoleService) {
        this.userRoleService = userRoleService;
    }

    @GetMapping
    public ResponseEntity<List<RoleResponseDto>> getAllRoles() {
        List<RoleResponseDto> res = userRoleService.getAll();
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<RoleResponseDto> getRoleById(@PathVariable String id) {
        RoleResponseDto res = userRoleService.getById(id);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

}
