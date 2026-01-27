package com.dansmultipro.ims.service;

import com.dansmultipro.ims.dto.CreateResponseDto;
import com.dansmultipro.ims.dto.DeleteResponseDto;
import com.dansmultipro.ims.dto.PaginatedResponseDto;
import com.dansmultipro.ims.dto.UpdateResponseDto;
import com.dansmultipro.ims.dto.productcategory.CreateProductCategoryRequestDto;
import com.dansmultipro.ims.dto.productcategory.ProductCategoryResponseDto;
import com.dansmultipro.ims.dto.productcategory.UpdateProductCategoryRequestDto;

public interface ProductCategoryService {

    PaginatedResponseDto<ProductCategoryResponseDto> getAll(Integer page, Integer size);

    ProductCategoryResponseDto getById(String id);

    CreateResponseDto create(CreateProductCategoryRequestDto requestDto);

    UpdateResponseDto update(String id, UpdateProductCategoryRequestDto requestDto);

    DeleteResponseDto deleteById(String id);

}
