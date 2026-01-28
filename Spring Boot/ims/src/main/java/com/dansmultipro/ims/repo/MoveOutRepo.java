package com.dansmultipro.ims.repo;

import com.dansmultipro.ims.model.MoveOut;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface MoveOutRepo extends JpaRepository<MoveOut, UUID> {

    Optional<MoveOut> findByCodeIgnoreCase(String code);

}
