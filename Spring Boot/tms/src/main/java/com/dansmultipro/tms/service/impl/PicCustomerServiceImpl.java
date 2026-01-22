package com.dansmultipro.tms.service.impl;

import com.dansmultipro.tms.dto.CreateResponseDto;
import com.dansmultipro.tms.dto.UpdateResponseDto;
import com.dansmultipro.tms.dto.piccustomer.CreatePicCustomerRequestDto;
import com.dansmultipro.tms.dto.piccustomer.PicCustomerResponseDto;
import com.dansmultipro.tms.dto.piccustomer.UpdatePicCustomerRequestDto;
import com.dansmultipro.tms.repository.PicCustomerRepo;
import com.dansmultipro.tms.service.PicCustomerService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PicCustomerServiceImpl extends BaseService implements PicCustomerService {

    private final PicCustomerRepo picCustomerRepo;

    public PicCustomerServiceImpl(PicCustomerRepo picCustomerRepo) {
        this.picCustomerRepo = picCustomerRepo;
    }

    @Override
    public List<PicCustomerResponseDto> getAll() {
        return List.of();
    }

    @Override
    public PicCustomerResponseDto getById(String id) {
        return null;
    }

    @Override
    public CreateResponseDto create(CreatePicCustomerRequestDto data) {
        return null;
    }

    @Override
    public UpdateResponseDto update(String id, UpdatePicCustomerRequestDto data) {
        return null;
    }

}
