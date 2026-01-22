package com.dansmultipro.tms.service.impl;

import com.dansmultipro.tms.model.BaseModel;

import java.time.LocalDateTime;
import java.util.UUID;

public abstract class BaseService {

    protected <T extends BaseModel> T prepareForInsert(T object) {
        object.setId(UUID.randomUUID());
        object.setCreatedAt(LocalDateTime.now());
        object.setCreatedBy(UUID.randomUUID());
        return object;
    }

    protected <T extends BaseModel> T prepareForUpdate(T object) {
        object.setUpdatedAt(LocalDateTime.now());
        object.setCreatedBy(UUID.randomUUID());
        return object;
    }

}
