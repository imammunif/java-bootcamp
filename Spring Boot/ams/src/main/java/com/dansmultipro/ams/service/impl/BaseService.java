package com.dansmultipro.ams.service.impl;

import com.dansmultipro.ams.model.BaseModel;
import com.dansmultipro.ams.service.PrincipalService;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.UUID;

public abstract class BaseService {

    protected PrincipalService principalService;

    protected <T extends BaseModel> T prepareForInsert(T object) {
        object.setId(UUID.randomUUID());
        object.setCreatedAt(LocalDateTime.now());
        object.setCreatedBy(UUID.fromString(principalService.getPrincipal().getId()));
        return object;
    }

    protected <T extends BaseModel> T prepareForUpdate(T object) {
        object.setUpdatedAt(LocalDateTime.now());
        object.setUpdatedBy(UUID.fromString(principalService.getPrincipal().getId()));
        return object;
    }

    @Autowired
    private void setPrincipalService(PrincipalService principalService) {
        this.principalService = principalService;
    }

}
