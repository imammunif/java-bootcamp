package com.dansmultipro.tms.service.impl;

import com.dansmultipro.tms.dto.CreateResponseDto;
import com.dansmultipro.tms.dto.UpdateResponseDto;
import com.dansmultipro.tms.dto.productcustomer.CreateProductCustomerRequestDto;
import com.dansmultipro.tms.dto.productcustomer.ProductCustomerResponseDto;
import com.dansmultipro.tms.dto.productcustomer.UpdateProductCustomerRequestDto;
import com.dansmultipro.tms.exception.NotFoundException;
import com.dansmultipro.tms.model.ProductCustomer;
import com.dansmultipro.tms.repository.ProductCustomerRepo;
import com.dansmultipro.tms.service.ProductCustomerService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProductCustomerServiceImpl extends BaseService implements ProductCustomerService {

    private final ProductCustomerRepo productCustomerRepo;

    public ProductCustomerServiceImpl(ProductCustomerRepo productCustomerRepo) {
        this.productCustomerRepo = productCustomerRepo;
    }

    @Override
    public List<ProductCustomerResponseDto> getAll() {
        List<ProductCustomerResponseDto> result = productCustomerRepo.findAll().stream()
                .map(v -> new ProductCustomerResponseDto(v.getId(), v.getProduct().getName(), v.getCustomer().getFullName()))
                .toList();
        return result;
    }

    @Override
    public ProductCustomerResponseDto getById(String id) {
        ProductCustomer productCustomer = productCustomerRepo.findById(UUID.fromString(id)).orElseThrow(
                () -> new NotFoundException("Product customer not found")
        );
        return new ProductCustomerResponseDto(productCustomer.getId(), productCustomer.getProduct().getName(), productCustomer.getCustomer().getFullName());
    }

    @Override
    public CreateResponseDto create(CreateProductCustomerRequestDto data) {
        return null;
    }

    @Override
    public UpdateResponseDto update(String id, UpdateProductCustomerRequestDto data) {
        return null;
    }

}
