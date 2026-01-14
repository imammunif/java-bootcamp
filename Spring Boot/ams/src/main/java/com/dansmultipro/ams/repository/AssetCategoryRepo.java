package com.dansmultipro.ams.repository;

import com.dansmultipro.ams.model.AssetCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AssetCategoryRepo extends JpaRepository<AssetCategory, UUID> {
}
