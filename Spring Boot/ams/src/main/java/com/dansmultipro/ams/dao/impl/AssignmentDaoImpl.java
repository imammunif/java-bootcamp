package com.dansmultipro.ams.dao.impl;

import com.dansmultipro.ams.dao.AssignmentDao;
import com.dansmultipro.ams.model.Assignment;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class AssignmentDaoImpl implements AssignmentDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Assignment> getAll() {
        String hql = "SELECT a FROM Assignment a";
        List<Assignment> result = em.createQuery(hql, Assignment.class)
                .getResultList();
        return result;
    }

    @Override
    public Optional<Assignment> getById(UUID id) {
        Assignment assignment = em.find(Assignment.class, id);
        return Optional.ofNullable(assignment);
    }

    @Override
    public Assignment insert(Assignment assignment) {
        em.persist(assignment);
        return assignment;
    }

    @Override
    public Assignment update(Assignment assignment) {
        Assignment assignmentUpdate = em.merge(assignment);
        return assignmentUpdate;
    }

}
