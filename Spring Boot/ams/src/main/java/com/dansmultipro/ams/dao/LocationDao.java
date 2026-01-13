package com.dansmultipro.ams.dao;

import com.dansmultipro.ams.model.Location;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface LocationDao {

    List<Location> getAll();

    Optional<Location> getById(UUID id);

    Location insert(Location location);

    Location update(Location location);

    void deleteById(UUID id);

}
