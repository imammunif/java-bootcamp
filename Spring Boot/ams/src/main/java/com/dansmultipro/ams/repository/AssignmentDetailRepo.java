package com.dansmultipro.ams.repository;

import com.dansmultipro.ams.model.AssignmentDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AssignmentDetailRepo extends JpaRepository<AssignmentDetail, UUID> {
}
