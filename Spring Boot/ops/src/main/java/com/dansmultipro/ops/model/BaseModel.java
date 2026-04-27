package com.dansmultipro.ops.model;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Version;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@MappedSuperclass
public abstract class BaseModel {

    @Id
    @Column(length = 36)
    private UUID id;

    @Version
    @Column(nullable = false)
    private Integer version;

    @Column(nullable = false, length = 36)
    private UUID createdBy;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(length = 36)
    private UUID updatedBy;

    @Column
    private LocalDateTime updatedAt;

}
