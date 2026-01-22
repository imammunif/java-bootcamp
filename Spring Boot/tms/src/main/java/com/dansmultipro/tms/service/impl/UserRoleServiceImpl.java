package com.dansmultipro.tms.service.impl;

import com.dansmultipro.tms.dto.userrole.RoleResponseDto;
import com.dansmultipro.tms.exception.NotFoundException;
import com.dansmultipro.tms.model.UserRole;
import com.dansmultipro.tms.repository.UserRoleRepo;
import com.dansmultipro.tms.service.UserRoleService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserRoleServiceImpl implements UserRoleService {

    private final UserRoleRepo userRoleRepo;

    public UserRoleServiceImpl(UserRoleRepo userRoleRepo) {
        this.userRoleRepo = userRoleRepo;
    }

    @Override
    public List<RoleResponseDto> getAll() {
        List<RoleResponseDto> result = userRoleRepo.findAll().stream()
                .map(v -> new RoleResponseDto(v.getId(), v.getName(), v.getCode()))
                .toList();
        return result;
    }

    @Override
    public RoleResponseDto getById(String id) {
        UserRole role = userRoleRepo.findById(UUID.fromString(id)).orElseThrow(
                () -> new NotFoundException("Role not found")
        );
        return new RoleResponseDto(role.getId(), role.getName(), role.getCode());
    }
}
