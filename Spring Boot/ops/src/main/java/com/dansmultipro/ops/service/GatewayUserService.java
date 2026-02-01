package com.dansmultipro.ops.service;

import com.dansmultipro.ops.dto.gatewayuser.GatewayUserResponseDto;

import java.util.List;

public interface GatewayUserService {

    List<GatewayUserResponseDto> getAllByGatewayId(String gatewayId);

}
