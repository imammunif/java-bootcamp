package com.dansmultipro.ams.repository;

import com.dansmultipro.ams.model.Asset;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AssetRepo extends JpaRepository<Asset, UUID> {
}
