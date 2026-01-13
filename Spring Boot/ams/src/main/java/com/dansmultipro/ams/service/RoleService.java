package com.dansmultipro.ams.service;

import com.dansmultipro.ams.dto.role.RoleResponseDto;

import java.util.List;
import java.util.UUID;

public interface RoleService {

    List<RoleResponseDto> getAll();

    RoleResponseDto getById(UUID id);

}
