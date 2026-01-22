package com.dansmultipro.tms.service.impl;

import com.dansmultipro.tms.dto.CreateResponseDto;
import com.dansmultipro.tms.dto.UpdateResponseDto;
import com.dansmultipro.tms.dto.piccustomer.CreatePicCustomerRequestDto;
import com.dansmultipro.tms.dto.piccustomer.PicCustomerResponseDto;
import com.dansmultipro.tms.dto.piccustomer.UpdatePicCustomerRequestDto;
import com.dansmultipro.tms.exception.NotFoundException;
import com.dansmultipro.tms.model.PicCustomer;
import com.dansmultipro.tms.repository.PicCustomerRepo;
import com.dansmultipro.tms.service.PicCustomerService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PicCustomerServiceImpl extends BaseService implements PicCustomerService {

    private final PicCustomerRepo picCustomerRepo;

    public PicCustomerServiceImpl(PicCustomerRepo picCustomerRepo) {
        this.picCustomerRepo = picCustomerRepo;
    }

    @Override
    public List<PicCustomerResponseDto> getAll() {
        List<PicCustomerResponseDto> result = picCustomerRepo.findAll().stream()
                .map(v -> new PicCustomerResponseDto(v.getId(), v.getUserPIC().getFullName(), v.getUserCustomer().getFullName()))
                .toList();
        return result;
    }

    @Override
    public PicCustomerResponseDto getById(String id) {
        PicCustomer picCustomer = picCustomerRepo.findById(UUID.fromString(id)).orElseThrow(
                () -> new NotFoundException("PIC-Customer not found")
        );
        return new PicCustomerResponseDto(picCustomer.getId(), picCustomer.getUserPIC().getFullName(), picCustomer.getUserCustomer().getFullName());
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
