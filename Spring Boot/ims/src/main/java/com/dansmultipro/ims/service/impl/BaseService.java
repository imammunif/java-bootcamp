package com.dansmultipro.ims.service.impl;

import com.dansmultipro.ims.exception.InvalidUuidException;
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
