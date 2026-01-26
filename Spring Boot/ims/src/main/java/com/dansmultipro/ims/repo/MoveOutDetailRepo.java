package com.dansmultipro.ims.repo;

import com.dansmultipro.ims.model.MoveOutDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface MoveOutDetailRepo extends JpaRepository<MoveOutDetail, UUID> {

    List<MoveOutDetail> findByMoveOutId(UUID moveOutId);

}
