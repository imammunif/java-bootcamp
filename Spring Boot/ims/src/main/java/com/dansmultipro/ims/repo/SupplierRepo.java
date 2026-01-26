package com.dansmultipro.ims.repo;

import com.dansmultipro.ims.model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SupplierRepo extends JpaRepository<Supplier, UUID> {
}
