package com.dansmultipro.ams.repository;

import com.dansmultipro.ams.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LocationRepo extends JpaRepository<Location, UUID> {
}
