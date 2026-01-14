package com.dansmultipro.ams.dao;

import com.dansmultipro.ams.model.AssignmentDetail;

import java.util.Optional;
import java.util.UUID;

public interface AssignmentDetailDao {

    Optional<AssignmentDetail> getById(UUID id);

    AssignmentDetail insert(AssignmentDetail assignmentDetail);

    AssignmentDetail update(AssignmentDetail assignmentDetail);

}
