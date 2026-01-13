package com.dansmultipro.ams.dao;

import com.dansmultipro.ams.model.Asset;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AssetDao {

    List<Asset> getAll();

    Optional<Asset> getById(UUID id);

    Asset insert(Asset asset);

    Asset update(Asset asset);

    void deleteById(UUID id);

    void upa();
}
