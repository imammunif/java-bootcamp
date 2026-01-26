package com.dansmultipro.ims.repo;

import com.dansmultipro.ims.model.MoveInDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface MoveInDetailRepo extends JpaRepository<MoveInDetail, UUID> {

    List<MoveInDetail> findByMoveInId(UUID moveInId);

}
