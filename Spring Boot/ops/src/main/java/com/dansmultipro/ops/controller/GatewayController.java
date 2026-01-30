package com.dansmultipro.ops.controller;

import com.dansmultipro.ops.dto.userrole.RoleResponseDto;
import com.dansmultipro.ops.service.GatewayService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("gateways")
public class GatewayController {

    private final GatewayService gatewayService;

    public GatewayController(GatewayService gatewayService) {
        this.gatewayService = gatewayService;
    }

    @GetMapping
    public ResponseEntity<List<RoleResponseDto>> getAllGateways() {
        List<RoleResponseDto> res = gatewayService.getAll();
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<RoleResponseDto> getGatewayById(@PathVariable String id) {
        RoleResponseDto res = gatewayService.getById(id);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

}
