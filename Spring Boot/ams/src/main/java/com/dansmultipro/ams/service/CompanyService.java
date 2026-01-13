package com.dansmultipro.ams.service;

import com.dansmultipro.ams.dto.CreateResponseDto;
import com.dansmultipro.ams.dto.DeleteResponseDto;
import com.dansmultipro.ams.dto.UpdateResponseDto;
import com.dansmultipro.ams.dto.company.CompanyRequestDto;
import com.dansmultipro.ams.dto.company.CompanyResponseDto;
import com.dansmultipro.ams.dto.company.UpdateCompanyRequestDto;

import java.util.List;
import java.util.UUID;

public interface CompanyService {

    List<CompanyResponseDto> getAll();

    CompanyResponseDto getById(UUID id);

    CreateResponseDto insert(CompanyRequestDto data);

    UpdateResponseDto update(UUID id, UpdateCompanyRequestDto data);

    DeleteResponseDto deleteById(UUID id);

}
