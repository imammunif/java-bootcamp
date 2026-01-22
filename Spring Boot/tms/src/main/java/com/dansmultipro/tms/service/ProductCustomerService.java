package com.dansmultipro.tms.service;


import com.dansmultipro.tms.dto.CreateResponseDto;
import com.dansmultipro.tms.dto.UpdateResponseDto;
import com.dansmultipro.tms.dto.productcustomer.CreateProductCustomerRequestDto;
import com.dansmultipro.tms.dto.productcustomer.ProductCustomerResponseDto;
import com.dansmultipro.tms.dto.productcustomer.UpdateProductCustomerRequestDto;

import java.util.List;

public interface ProductCustomerService {

    List<ProductCustomerResponseDto> getAll();

    ProductCustomerResponseDto getById(String id);

    CreateResponseDto create(CreateProductCustomerRequestDto data);

    UpdateResponseDto update(String id, UpdateProductCustomerRequestDto data);

}
