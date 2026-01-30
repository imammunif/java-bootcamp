package com.dansmultipro.ops.service;

import com.dansmultipro.ops.dto.CommonResponseDto;
import com.dansmultipro.ops.dto.gatewayuser.CreateGatewayUserRequestDto;
import com.dansmultipro.ops.repository.GatewayUserRepo;

public interface GatewayUserService {

    GatewayUserRepo getById(String id);

    CommonResponseDto create(CreateGatewayUserRequestDto requestDto);

}
