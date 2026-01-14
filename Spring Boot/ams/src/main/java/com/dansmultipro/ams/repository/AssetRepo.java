package com.dansmultipro.ams.repository;

import com.dansmultipro.ams.model.Asset;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AssetRepo extends JpaRepository<Asset, UUID> {
}
