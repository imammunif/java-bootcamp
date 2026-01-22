package com.dansmultipro.tms.service;


import com.dansmultipro.tms.dto.CreateResponseDto;
import com.dansmultipro.tms.dto.DeleteResponseDto;
import com.dansmultipro.tms.dto.UpdateResponseDto;
import com.dansmultipro.tms.dto.company.CompanyResponseDto;
import com.dansmultipro.tms.dto.company.CreateCompanyRequestDto;
import com.dansmultipro.tms.dto.company.UpdateCompanyRequestDto;

import java.util.List;

public interface CompanyService {

    List<CompanyResponseDto> getAll();

    CompanyResponseDto getById(String id);

    CreateResponseDto create(CreateCompanyRequestDto data);

    UpdateResponseDto update(String id, UpdateCompanyRequestDto data);

    DeleteResponseDto deleteById(String id);

}
