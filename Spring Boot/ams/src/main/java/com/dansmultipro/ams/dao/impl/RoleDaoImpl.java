package com.dansmultipro.ams.dao.impl;

import com.dansmultipro.ams.dao.RoleDao;
import com.dansmultipro.ams.model.Role;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class RoleDaoImpl implements RoleDao {

    @PersistenceContext
    private EntityManager em;

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
        return Optional.ofNullable(role);
    }

}
