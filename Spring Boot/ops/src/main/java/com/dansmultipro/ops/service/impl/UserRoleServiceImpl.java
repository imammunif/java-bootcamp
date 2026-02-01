package com.dansmultipro.ops.service.impl;

import com.dansmultipro.ops.dto.userrole.RoleResponseDto;
import com.dansmultipro.ops.exception.NotFoundException;
import com.dansmultipro.ops.model.UserRole;
import com.dansmultipro.ops.repository.UserRoleRepo;
import com.dansmultipro.ops.service.UserRoleService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserRoleServiceImpl extends BaseService implements UserRoleService {

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
        UUID validId = validateUUID(id);
        UserRole role = userRoleRepo.findById(validId).orElseThrow(
                () -> new NotFoundException("Role not found")
        );
        return new RoleResponseDto(role.getId(), role.getName(), role.getCode());
    }

}
