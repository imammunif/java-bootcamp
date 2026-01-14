package com.dansmultipro.ams.repository;

import com.dansmultipro.ams.model.Assignment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AssignmentRepo extends JpaRepository<Assignment, UUID> {
}
