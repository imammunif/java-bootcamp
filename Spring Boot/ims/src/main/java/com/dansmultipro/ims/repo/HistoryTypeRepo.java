package com.dansmultipro.ims.repo;

import com.dansmultipro.ims.model.HistoryType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface HistoryTypeRepo extends JpaRepository<HistoryType, UUID> {
}
