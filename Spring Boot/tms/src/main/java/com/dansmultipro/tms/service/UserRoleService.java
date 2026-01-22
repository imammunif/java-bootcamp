package com.dansmultipro.tms.service;

import com.dansmultipro.tms.dto.userrole.RoleResponseDto;

import java.util.List;

public interface UserRoleService {

    List<RoleResponseDto> getAll();

    RoleResponseDto getById(String id);

}