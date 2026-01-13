package com.dansmultipro.ams.dao.impl;

import com.dansmultipro.ams.dao.RoleDao;
import com.dansmultipro.ams.model.Role;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class RoleDaoImpl implements RoleDao {

    @Override
    public Role insert(Role role) {
        return null;
    }

    @Override
    public Role update(Role role) {
        return null;
    }

    @Override
    public List<Role> getAll() {
        return List.of();
    }

    @Override
    public Optional<Role> getById(UUID id) {
        return Optional.empty();
    }

    @Override
    public void deleteRole(UUID id) {

    }
}
