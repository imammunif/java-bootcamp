package com.dansmultipro.ops.service.impl;


import com.dansmultipro.ops.exception.InvalidUuidException;
import com.dansmultipro.ops.model.BaseModel;
import com.dansmultipro.ops.service.PrincipalService;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.UUID;

public class BaseService {

    protected PrincipalService principalService;

    @Autowired
    public void setPrincipalService(PrincipalService principalService) {
        this.principalService = principalService;
    }

    protected <T extends BaseModel> T prepareForInsert(T object) {
        object.setId(UUID.randomUUID());
        object.setCreatedAt(LocalDateTime.now());
        object.setCreatedBy(principalService.getPrincipal().getId());
        return object;
    }

    protected <T extends BaseModel> T prepareForUpdate(T object) {
        object.setUpdatedAt(LocalDateTime.now());
        object.setUpdatedBy(principalService.getPrincipal().getId());
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
