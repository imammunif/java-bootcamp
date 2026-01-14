package com.dansmultipro.ams.service;

import com.dansmultipro.ams.dto.CreateResponseDto;
import com.dansmultipro.ams.dto.DeleteResponseDto;
import com.dansmultipro.ams.dto.UpdateResponseDto;
import com.dansmultipro.ams.dto.company.CompanyRequestDto;
import com.dansmultipro.ams.dto.company.CompanyResponseDto;
import com.dansmultipro.ams.dto.company.UpdateCompanyRequestDto;

import java.util.List;

public interface CompanyService {

    List<CompanyResponseDto> getAll();

    CompanyResponseDto getById(String id);

    CreateResponseDto insert(CompanyRequestDto data);

    UpdateResponseDto update(String id, UpdateCompanyRequestDto data);

    DeleteResponseDto deleteById(String id);

}
