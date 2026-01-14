package com.dansmultipro.ams.repository;

import com.dansmultipro.ams.model.AssignmentDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AssignmentDetailRepo extends JpaRepository<AssignmentDetail, UUID> {
}
