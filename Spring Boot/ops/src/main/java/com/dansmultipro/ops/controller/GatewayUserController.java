package com.dansmultipro.ops.controller;

import com.dansmultipro.ops.repository.GatewayUserRepo;
import com.dansmultipro.ops.service.GatewayUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("gateways")
public class GatewayUserController {

    private final GatewayUserService gatewayUserService;

    public GatewayUserController(GatewayUserService gatewayUserService) {
        this.gatewayUserService = gatewayUserService;
    }

    @GetMapping("{id}")
    public ResponseEntity<GatewayUserRepo> getGatewayUsersById(@PathVariable String id) {
        GatewayUserRepo res = gatewayUserService.getById(id);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

}
