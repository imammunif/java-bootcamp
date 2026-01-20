package com.dansmultipro.ams.service.impl;

import com.dansmultipro.ams.dto.role.RoleResponseDto;
import com.dansmultipro.ams.model.Role;
import com.dansmultipro.ams.repository.RoleRepo;
import com.dansmultipro.ams.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepo roleRepo;

    @Autowired
    public RoleServiceImpl(RoleRepo roleRepo) {
        this.roleRepo = roleRepo;
    }

    @Override
    public List<RoleResponseDto> getAll() {
        List<RoleResponseDto> result = roleRepo.findAll().stream()
                .map(v -> new RoleResponseDto(v.getId(), v.getName(), v.getCode()))
                .toList();
        return result;
    }

    @Override
    public RoleResponseDto getById(String id) {
        Role role = roleRepo.findById(UUID.fromString(id)).orElseThrow(
                () -> new RuntimeException("Role not found")
        );
        return new RoleResponseDto(role.getId(), role.getName(), role.getCode());
    }

}
