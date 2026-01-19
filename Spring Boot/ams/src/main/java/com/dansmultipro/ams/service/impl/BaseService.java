package com.dansmultipro.ams.service.impl;

import com.dansmultipro.ams.model.BaseModel;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.time.LocalDateTime;
import java.util.UUID;

public abstract class BaseService {

    @PersistenceContext
    protected EntityManager em;

    protected <T extends BaseModel> T prepareForInsert(T object) {
        object.setId(UUID.randomUUID());
        object.setCreatedAt(LocalDateTime.now());
        object.setCreatedBy(UUID.randomUUID());
        return object;
    }

    protected <T extends BaseModel> T prepareForUpdate(T object) {
        object.setUpdatedAt(LocalDateTime.now());
        object.setUpdatedBy(UUID.randomUUID());
        return object;
    }

}
