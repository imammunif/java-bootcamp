package com.dansmultipro.tms.service;


import com.dansmultipro.tms.dto.CreateResponseDto;
import com.dansmultipro.tms.dto.DeleteResponseDto;
import com.dansmultipro.tms.dto.UpdateResponseDto;
import com.dansmultipro.tms.dto.product.CreateProductRequestDto;
import com.dansmultipro.tms.dto.product.ProductResponseDto;
import com.dansmultipro.tms.dto.product.UpdateProductRequestDto;

import java.util.List;

public interface ProductService {

    List<ProductResponseDto> getAll();

    ProductResponseDto getById(String id);

    CreateResponseDto create(CreateProductRequestDto data);

    UpdateResponseDto update(String id, UpdateProductRequestDto data);

    DeleteResponseDto deleteById(String id);

}
