package com.dansmultipro.tms.repository;

import com.dansmultipro.tms.model.ProductCustomer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductCustomerRepo extends JpaRepository<ProductCustomer, UUID> {
}
