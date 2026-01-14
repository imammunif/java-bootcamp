package com.dansmultipro.ams.controller;

import com.dansmultipro.ams.dto.role.RoleResponseDto;
import com.dansmultipro.ams.service.RoleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("roles")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping
    public ResponseEntity<List<RoleResponseDto>> getAllRoles() {
        List<RoleResponseDto> res = roleService.getAll();
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<RoleResponseDto> getRoleById(@PathVariable String id) {
        RoleResponseDto res = roleService.getById(id);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

}
