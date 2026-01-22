package com.dansmultipro.tms.repository;

import com.dansmultipro.tms.model.PicCustomer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PicCustomerRepo extends JpaRepository<PicCustomer, UUID> {

    List<PicCustomer> findByPicIdAndCustomerIdNotIn(UUID picId, List<UUID> customerIdList);

    Optional<PicCustomer> findByCustomerId(UUID customerId);

}
