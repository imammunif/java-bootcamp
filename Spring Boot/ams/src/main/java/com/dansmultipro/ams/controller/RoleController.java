package com.dansmultipro.ams.controller;

import com.dansmultipro.ams.dto.employee.EmployeeResponse;
import com.dansmultipro.ams.dto.role.RoleResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("roles")
public class RoleController {

    @GetMapping
    public List<RoleResponse> getAllRoles() {
        return null;
    }

    @GetMapping("{id}")
    public RoleResponse getRoleById(@PathVariable String id) {
        return null;
    }

}
