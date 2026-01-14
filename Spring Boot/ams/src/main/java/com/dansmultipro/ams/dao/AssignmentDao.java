package com.dansmultipro.ams.dao;

import com.dansmultipro.ams.model.Assignment;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AssignmentDao {

    List<Assignment> getAll();

    Optional<Assignment> getById(UUID id);

    Assignment insert(Assignment assignment);

    Assignment update(Assignment assignment);

}
