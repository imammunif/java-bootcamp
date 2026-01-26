package com.dansmultipro.ims.service.impl;

import com.dansmultipro.ims.model.BaseModel;

import java.time.LocalDateTime;
import java.util.UUID;

public class BaseService {

    protected <T extends BaseModel> T prepareForInsert(T object) {
        object.setId(UUID.randomUUID());
        object.setCreatedAt(LocalDateTime.now());
        return object;
    }

    protected <T extends BaseModel> T prepareForUpdate(T object) {
        object.setUpdatedAt(LocalDateTime.now());
        return object;
    }

}
