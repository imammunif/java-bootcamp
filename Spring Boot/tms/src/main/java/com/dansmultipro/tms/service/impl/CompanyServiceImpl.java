package com.dansmultipro.tms.service.impl;


import com.dansmultipro.tms.dto.CreateResponseDto;
import com.dansmultipro.tms.dto.DeleteResponseDto;
import com.dansmultipro.tms.dto.UpdateResponseDto;
import com.dansmultipro.tms.dto.company.CompanyResponseDto;
import com.dansmultipro.tms.dto.company.CreateCompanyRequestDto;
import com.dansmultipro.tms.dto.company.UpdateCompanyRequestDto;
import com.dansmultipro.tms.exception.DataMissMatchException;
import com.dansmultipro.tms.exception.NotFoundException;
import com.dansmultipro.tms.model.Company;
import com.dansmultipro.tms.repository.CompanyRepo;
import com.dansmultipro.tms.service.CompanyService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CompanyServiceImpl extends BaseService implements CompanyService {

    private final CompanyRepo companyRepo;

    public CompanyServiceImpl(CompanyRepo companyRepo) {
        this.companyRepo = companyRepo;
    }

    @Override
    public List<CompanyResponseDto> getAll() {
        List<CompanyResponseDto> result = companyRepo.findAll().stream()
                .map(v -> new CompanyResponseDto(v.getId(), v.getName()))
                .toList();
        return result;
    }

    @Override
    public CompanyResponseDto getById(String id) {
        Company company = companyRepo.findById(UUID.fromString(id)).orElseThrow(
                () -> new RuntimeException("Company not found")
        );
        return new CompanyResponseDto(company.getId(), company.getName());
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public CreateResponseDto create(CreateCompanyRequestDto request) {
        Company companyNew = new Company();
        Company companyInsert = prepareForInsert(companyNew);
        companyInsert.setName(request.getName());
        Company company = companyRepo.save(companyInsert);
        return new CreateResponseDto(company.getId(), "Saved");
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public UpdateResponseDto update(String id, UpdateCompanyRequestDto request) {
        Company company = companyRepo.findById(UUID.fromString(id)).orElseThrow(
                () -> new NotFoundException("Company not found")
        );
        if (!company.getVersion().equals(request.getVersion())) {
            throw new DataMissMatchException("Version not match");
        }
        Company companyUpdate = prepareForUpdate(company);
        companyUpdate.setName(request.getName());
        companyRepo.saveAndFlush(companyUpdate);
        return new UpdateResponseDto(companyUpdate.getVersion(), "Updated");
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public DeleteResponseDto deleteById(String id) {
        Company company = companyRepo.findById(UUID.fromString(id)).orElseThrow(
                () -> new RuntimeException("Company not found")
        );
        companyRepo.deleteById(company.getId());
        return new DeleteResponseDto("Deleted");
    }

}
