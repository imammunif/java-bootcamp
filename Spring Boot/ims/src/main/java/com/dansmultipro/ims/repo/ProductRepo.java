package com.dansmultipro.ims.repo;

import com.dansmultipro.ims.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductRepo extends JpaRepository<Product, UUID> {

    Boolean existsByCategoryId(UUID categoryId);

}
