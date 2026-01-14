package com.dansmultipro.ams.service;

import com.dansmultipro.ams.dto.role.RoleResponseDto;

import java.util.List;

public interface RoleService {

    List<RoleResponseDto> getAll();

    RoleResponseDto getById(String id);

}
