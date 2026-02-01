package com.dansmultipro.ops.service.impl;


import com.dansmultipro.ops.exception.InvalidUuidException;
import com.dansmultipro.ops.model.BaseModel;

import java.time.LocalDateTime;
import java.util.UUID;

public class BaseService {

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
