package com.dansmultipro.tms.service.impl;

import com.dansmultipro.tms.model.BaseModel;
import com.dansmultipro.tms.service.PrincipalService;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.UUID;

public abstract class BaseService {

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


}
