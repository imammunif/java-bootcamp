package com.dansmultipro.ims.repo;

import com.dansmultipro.ims.model.MoveHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface MoveHistoryRepo extends JpaRepository<MoveHistory, UUID> {

    List<MoveHistory> findByProductId(UUID productId);

}
