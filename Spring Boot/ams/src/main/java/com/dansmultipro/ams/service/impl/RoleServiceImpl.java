package com.dansmultipro.ams.service.impl;

import com.dansmultipro.ams.dao.RoleDao;
import com.dansmultipro.ams.dto.role.RoleResponseDto;
import com.dansmultipro.ams.model.Role;
import com.dansmultipro.ams.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleDao roleDao;

    @Autowired
    public RoleServiceImpl(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public List<RoleResponseDto> getAll() {
        List<RoleResponseDto> result = roleDao.getAll().stream()
                .map(v -> new RoleResponseDto(v.getId(), v.getRoleCode(), v.getRoleName()))
                .toList();
        return result;
    }

    @Override
    public RoleResponseDto getById(UUID id) {
        Role role = roleDao.getById(id).orElseThrow(
                () -> new RuntimeException("Role not found")
        );
        return new RoleResponseDto(id, role.getRoleCode(), role.getRoleName());
    }

}
