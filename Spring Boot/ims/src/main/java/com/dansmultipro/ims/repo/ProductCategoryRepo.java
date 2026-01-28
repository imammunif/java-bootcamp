package com.dansmultipro.ims.repo;

import com.dansmultipro.ims.model.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ProductCategoryRepo extends JpaRepository<ProductCategory, UUID> {

    Optional<ProductCategory> findByCodeIgnoreCase(String code);

}
