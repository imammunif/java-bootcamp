package com.dansmultipro.ops.service.impl;


import com.dansmultipro.ops.constant.RoleCode;
import com.dansmultipro.ops.exception.InvalidUuidException;
import com.dansmultipro.ops.exception.NotFoundException;
import com.dansmultipro.ops.model.BaseModel;
import com.dansmultipro.ops.model.UserRole;
import com.dansmultipro.ops.repository.UserRoleRepo;
import com.dansmultipro.ops.service.PrincipalService;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.UUID;

public class BaseService {

    protected PrincipalService principalService;
    private UserRoleRepo userRoleRepo;

    @Autowired
    public void setPrincipalService(PrincipalService principalService) {
        this.principalService = principalService;
    }

    @Autowired
    public void setUserRoleRepo(UserRoleRepo userRoleRepo) {
        this.userRoleRepo = userRoleRepo;
    }

    protected <T extends BaseModel> T prepareForInsert(T object) {
        object.setId(UUID.randomUUID());
        object.setCreatedAt(LocalDateTime.now());
        object.setCreatedBy(principalService.getPrincipal().getId());
        return object;
    }

    protected <T extends BaseModel> T insertBySystem(T object) {
        UserRole system = userRoleRepo.findByCode(RoleCode.SYSTEM.getCode()).orElseThrow(
                () -> new NotFoundException("System role not found")
        );
        object.setId(UUID.randomUUID());
        object.setCreatedAt(LocalDateTime.now());
        object.setCreatedBy(system.getId());
        return object;
    }

    protected <T extends BaseModel> T prepareForUpdate(T object) {
        object.setUpdatedAt(LocalDateTime.now());
        object.setUpdatedBy(principalService.getPrincipal().getId());
        return object;
    }

    protected <T extends BaseModel> T updateBySystem(T object) {
        UserRole system = userRoleRepo.findByCode(RoleCode.SYSTEM.getCode()).orElseThrow(
                () -> new NotFoundException("System role not found")
        );
        object.setUpdatedAt(LocalDateTime.now());
        object.setUpdatedBy(system.getId());
        return object;
    }

    protected UUID validateUUID(String id) {
        if (id.isBlank()) {
            throw new InvalidUuidException("Invalid ID format");
        }
        try {
            UUID validId = UUID.fromString(id);
            return validId;
        } catch (IllegalArgumentException ex) {
            throw new InvalidUuidException("Invalid ID format");
        }
    }

}
