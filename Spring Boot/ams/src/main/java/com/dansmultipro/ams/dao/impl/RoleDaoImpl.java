package com.dansmultipro.ams.dao.impl;

import com.dansmultipro.ams.dao.RoleDao;
import com.dansmultipro.ams.model.Role;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class RoleDaoImpl implements RoleDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Role insert(Role role) {
        // insert should use persis, can't use manual query
        em.persist(role);
        return role;
    }

    @Override
    public Role update(Role role) {
        Role roleUpdate = em.merge(role);
        return roleUpdate;
    }

    // not provided from JPA, need to use HQL
    @Override
    public List<Role> getAll() {
        String hql = "SELECT r FROM Role r";
        List<Role> result = em.createQuery(hql, Role.class)
                .getResultList();
        return result;
    }

    @Override
    public Optional<Role> getById(UUID id) {
        Role role = em.find(Role.class, id);
        // should be careful because its entering manage state
        return Optional.ofNullable(role);
    }

    @Override
    public void deleteRole(UUID id) {
        Role role = em.find(Role.class, id);
        em.remove(role);
    }
}
