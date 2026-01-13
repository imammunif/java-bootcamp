package com.dansmultipro.ams.dao;

import com.dansmultipro.ams.model.AssetCategory;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AssetCategoryDao {

    List<AssetCategory> getAll();

    Optional<AssetCategory> getById(UUID id);

}
