package com.dansmultipro.tms.service;


import com.dansmultipro.tms.dto.CommonResponseDto;
import com.dansmultipro.tms.dto.productcustomer.CreateProductCustomerRequestDto;
import com.dansmultipro.tms.dto.productcustomer.ProductCustomerResponseDto;
import com.dansmultipro.tms.dto.productcustomer.UpdateProductCustomerRequestDto;

import java.util.List;

public interface ProductCustomerService {

    List<ProductCustomerResponseDto> getAll();

    ProductCustomerResponseDto getById(String id);

    CommonResponseDto create(CreateProductCustomerRequestDto data);

    CommonResponseDto update(String id, UpdateProductCustomerRequestDto data);

}
