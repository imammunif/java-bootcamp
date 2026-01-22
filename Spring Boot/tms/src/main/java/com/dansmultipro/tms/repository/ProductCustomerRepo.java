package com.dansmultipro.tms.repository;

import com.dansmultipro.tms.model.ProductCustomer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductCustomerRepo extends JpaRepository<ProductCustomer, UUID> {

    List<ProductCustomer> findByProductIdAndCustomerIdNotIn(UUID customerId, List<UUID> productIdList);

    Optional<ProductCustomer> findByProductId(UUID productId);

}
