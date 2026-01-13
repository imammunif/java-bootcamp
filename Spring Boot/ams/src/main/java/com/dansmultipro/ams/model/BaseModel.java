package com.dansmultipro.ams.model;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Version;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;
import java.util.UUID;

@MappedSuperclass
public abstract class BaseModel {

    @Id
    @Column(length = 36)
    private UUID id;

    @Version
    @Column(nullable = false)
    private Integer version;

    @Column(nullable = false, length = 36)
    private String createdBy;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(length = 36)
    private String updatedBy;

    @Column
    private LocalDateTime updatedAt;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

}
