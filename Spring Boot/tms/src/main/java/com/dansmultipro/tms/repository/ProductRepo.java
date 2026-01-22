package com.dansmultipro.tms.repository;

import com.dansmultipro.tms.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductRepo extends JpaRepository<Product, UUID> {
}
