package com.dansmultipro.tms.service;


import com.dansmultipro.tms.dto.CreateResponseDto;
import com.dansmultipro.tms.dto.UpdateResponseDto;
import com.dansmultipro.tms.dto.piccustomer.CreatePicCustomerRequestDto;
import com.dansmultipro.tms.dto.piccustomer.PicCustomerResponseDto;
import com.dansmultipro.tms.dto.piccustomer.UpdatePicCustomerRequestDto;

import java.util.List;

public interface PicCustomerService {

    List<PicCustomerResponseDto> getAll();

    PicCustomerResponseDto getById(String id);

    CreateResponseDto create(CreatePicCustomerRequestDto data);

    UpdateResponseDto update(String id, UpdatePicCustomerRequestDto data);

}
