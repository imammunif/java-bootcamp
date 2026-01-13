package com.dansmultipro.ams.dao;

import com.dansmultipro.ams.model.AssetStatus;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AssetStatusDao {

    List<AssetStatus> getAll();

    Optional<AssetStatus> getById(UUID id);

}
