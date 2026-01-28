package com.dansmultipro.ims.repo;

import com.dansmultipro.ims.model.MoveIn;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface MoveInRepo extends JpaRepository<MoveIn, UUID> {

    Optional<MoveIn> findByCodeIgnoreCase(String code);

}
