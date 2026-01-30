package com.dansmultipro.ops.service;

import com.dansmultipro.ops.dto.userrole.RoleResponseDto;

import java.util.List;

public interface GatewayService {

    List<RoleResponseDto> getAll();

    RoleResponseDto getById(String id);

}
