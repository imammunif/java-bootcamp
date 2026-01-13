package com.dansmultipro.ams.dao;

import com.dansmultipro.ams.model.Role;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RoleDao {
    Role insert(Role role);
    Role update(Role role);
    List<Role> getAll();
    // use optional so no need to check
    Optional<Role> getById(UUID id);
    void deleteRole(UUID id);
}
