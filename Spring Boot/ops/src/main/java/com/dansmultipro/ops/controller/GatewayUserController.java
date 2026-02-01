package com.dansmultipro.ops.controller;

import com.dansmultipro.ops.dto.gatewayuser.GatewayUserResponseDto;
import com.dansmultipro.ops.service.GatewayUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("gateways")
public class GatewayUserController {

    private final GatewayUserService gatewayUserService;

    public GatewayUserController(GatewayUserService gatewayUserService) {
        this.gatewayUserService = gatewayUserService;
    }

    @GetMapping("{gateway_id}/users")
    public ResponseEntity<List<GatewayUserResponseDto>> getAllByGatewayId(
            @PathVariable("gateway_id") String gatewayId
    ) {
        List<GatewayUserResponseDto> res = gatewayUserService.getAllByGatewayId(gatewayId);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

}
