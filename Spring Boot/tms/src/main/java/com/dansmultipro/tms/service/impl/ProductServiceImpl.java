package com.dansmultipro.tms.service.impl;

import com.dansmultipro.tms.dto.CreateResponseDto;
import com.dansmultipro.tms.dto.DeleteResponseDto;
import com.dansmultipro.tms.dto.UpdateResponseDto;
import com.dansmultipro.tms.dto.product.CreateProductRequestDto;
import com.dansmultipro.tms.dto.product.ProductResponseDto;
import com.dansmultipro.tms.dto.product.UpdateProductRequestDto;
import com.dansmultipro.tms.exception.NotFoundException;
import com.dansmultipro.tms.model.Product;
import com.dansmultipro.tms.repository.ProductRepo;
import com.dansmultipro.tms.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProductServiceImpl extends BaseService implements ProductService {

    private final ProductRepo productRepo;

    public ProductServiceImpl(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    @Override
    public List<ProductResponseDto> getAll() {
        List<ProductResponseDto> result = productRepo.findAll().stream()
                .map(v -> new ProductResponseDto(v.getId(), v.getName()))
                .toList();
        return result;
    }

    @Override
    public ProductResponseDto getById(String id) {
        Product product = productRepo.findById(UUID.fromString(id)).orElseThrow(
                () -> new NotFoundException("Product not found")
        );
        return new ProductResponseDto(product.getId(), product.getName());
    }

    @Override
    public CreateResponseDto create(CreateProductRequestDto data) {
        return null;
    }

    @Override
    public UpdateResponseDto update(String id, UpdateProductRequestDto data) {
        return null;
    }

    @Override
    public DeleteResponseDto deleteById(String id) {
        return null;
    }

}
