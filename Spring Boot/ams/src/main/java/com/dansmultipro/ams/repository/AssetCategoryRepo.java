package com.dansmultipro.ams.repository;

import com.dansmultipro.ams.model.AssetCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AssetCategoryRepo extends JpaRepository<AssetCategory, UUID> {
}
