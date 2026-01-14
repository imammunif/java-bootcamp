package com.dansmultipro.ams.repository;

import com.dansmultipro.ams.model.AssetStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AssetStatusRepo extends JpaRepository<AssetStatus, UUID> {
}
