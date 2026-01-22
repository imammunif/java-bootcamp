package com.dansmultipro.tms.service;


import com.dansmultipro.tms.dto.CommonResponseDto;
import com.dansmultipro.tms.dto.piccustomer.CreatePicCustomerRequestDto;
import com.dansmultipro.tms.dto.piccustomer.PicCustomerResponseDto;
import com.dansmultipro.tms.dto.piccustomer.UpdatePicCustomerRequestDto;

import java.util.List;

public interface PicCustomerService {

    List<PicCustomerResponseDto> getAll();

    PicCustomerResponseDto getById(String id);

    CommonResponseDto create(CreatePicCustomerRequestDto data);

    CommonResponseDto update(String id, UpdatePicCustomerRequestDto data);

}
