package com.dansmultipro.ams.controller;

import com.dansmultipro.ams.dto.user.UserSaveDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class UserController {

    @GetMapping("welcome")
    public String hello(@RequestParam(required = false) String name) {
        return "Hello, " + name;
    }

    @GetMapping("welcome2")
    public String hello2() {
        return "Hello 2";
    }

    @PostMapping()
    public String save(@RequestBody UserSaveDto data) {
        // Request Body only allow single parameter
        return data.getFullName() + " saved";
    }
}
