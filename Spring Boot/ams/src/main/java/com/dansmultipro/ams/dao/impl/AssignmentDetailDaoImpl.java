package com.dansmultipro.ams.dao.impl;

import com.dansmultipro.ams.dao.AssignmentDetailDao;
import com.dansmultipro.ams.model.AssignmentDetail;
import com.dansmultipro.ams.model.Company;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class AssignmentDetailDaoImpl implements AssignmentDetailDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Optional<AssignmentDetail> getById(UUID id) {
        AssignmentDetail assignmentDetail = em.find(AssignmentDetail.class, id);
        return Optional.ofNullable(assignmentDetail);
    }

    @Override
    public AssignmentDetail insert(AssignmentDetail assignmentDetail) {
        em.persist(assignmentDetail);
        return assignmentDetail;
    }

    @Override
    public AssignmentDetail update(AssignmentDetail assignmentDetail) {
        AssignmentDetail assignmentDetailUpdate = em.merge(assignmentDetail);
        return assignmentDetailUpdate;
    }

}
