package com.dansmultipro.ams.dao;

import com.dansmultipro.ams.model.Role;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RoleDao {

    List<Role> getAll();

    Optional<Role> getById(UUID id);

}
