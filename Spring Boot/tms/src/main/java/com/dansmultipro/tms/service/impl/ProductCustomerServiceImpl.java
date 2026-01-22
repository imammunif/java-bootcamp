package com.dansmultipro.tms.service.impl;

import com.dansmultipro.tms.dto.CreateResponseDto;
import com.dansmultipro.tms.dto.UpdateResponseDto;
import com.dansmultipro.tms.dto.productcustomer.CreateProductCustomerRequestDto;
import com.dansmultipro.tms.dto.productcustomer.ProductCustomerResponseDto;
import com.dansmultipro.tms.dto.productcustomer.UpdateProductCustomerRequestDto;
import com.dansmultipro.tms.repository.ProductCustomerRepo;
import com.dansmultipro.tms.service.ProductCustomerService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductCustomerServiceImpl extends BaseService implements ProductCustomerService {

    private final ProductCustomerRepo productCustomerRepo;

    public ProductCustomerServiceImpl(ProductCustomerRepo productCustomerRepo) {
        this.productCustomerRepo = productCustomerRepo;
    }

    @Override
    public List<ProductCustomerResponseDto> getAll() {
        return List.of();
    }

    @Override
    public ProductCustomerResponseDto getById(String id) {
        return null;
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
