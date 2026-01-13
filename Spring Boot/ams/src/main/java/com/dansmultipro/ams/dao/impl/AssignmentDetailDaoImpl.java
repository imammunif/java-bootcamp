package com.dansmultipro.ams.dao.impl;

import com.dansmultipro.ams.dao.AssignmentDetailDao;
import com.dansmultipro.ams.model.AssignmentDetail;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class AssignmentDetailDaoImpl implements AssignmentDetailDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public AssignmentDetail insert(AssignmentDetail assignmentDetail) {
        em.persist(assignmentDetail);
        return assignmentDetail;
    }

}
