package com.dansmultipro.ims.service;

import com.dansmultipro.ims.dto.CreateResponseDto;
import com.dansmultipro.ims.dto.DeleteResponseDto;
import com.dansmultipro.ims.dto.PaginatedResponseDto;
import com.dansmultipro.ims.dto.UpdateResponseDto;
import com.dansmultipro.ims.dto.supplier.CreateSupplierRequestDto;
import com.dansmultipro.ims.dto.supplier.SupplierResponseDto;
import com.dansmultipro.ims.dto.supplier.UpdateSupplierRequestDto;

public interface SupplierService {

    PaginatedResponseDto<SupplierResponseDto> getAll(Integer page, Integer size);

    SupplierResponseDto getById(String id);

    CreateResponseDto create(CreateSupplierRequestDto requestDto);

    UpdateResponseDto update(String id, UpdateSupplierRequestDto requestDto);

    DeleteResponseDto delete(String id);

}
