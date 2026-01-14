package com.dansmultipro.ams.repository;

import com.dansmultipro.ams.model.AssetStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AssetStatusRepo extends JpaRepository<AssetStatus, UUID> {
}
