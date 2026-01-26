package com.dansmultipro.ims.service;

import com.dansmultipro.ims.dto.CreateResponseDto;
import com.dansmultipro.ims.dto.DeleteResponseDto;
import com.dansmultipro.ims.dto.UpdateResponseDto;
import com.dansmultipro.ims.dto.product.CreateProductRequestDto;
import com.dansmultipro.ims.dto.product.ProductResponseDto;
import com.dansmultipro.ims.dto.product.UpdateProductRequestDto;

import java.util.List;

public interface ProductService {

    List<ProductResponseDto> getAll();

    ProductResponseDto getById(String id);

    CreateResponseDto create(CreateProductRequestDto requestDto);

    UpdateResponseDto update(String id, UpdateProductRequestDto requestDto);

    DeleteResponseDto deleteById(String id);

}
