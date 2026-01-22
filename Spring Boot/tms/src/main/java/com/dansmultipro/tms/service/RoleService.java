package com.dansmultipro.tms.service;

import com.dansmultipro.tms.dto.role.RoleResponseDto;

import java.util.List;

public interface RoleService {

    List<RoleResponseDto> getAll();

    RoleResponseDto getById(String id);

}