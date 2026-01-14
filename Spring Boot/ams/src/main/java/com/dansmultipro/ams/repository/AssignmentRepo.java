package com.dansmultipro.ams.repository;

import com.dansmultipro.ams.model.Assignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AssignmentRepo extends JpaRepository<Assignment, UUID> {
}
