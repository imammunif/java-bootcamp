package com.dansmultipro.ams.service.impl;

import com.dansmultipro.ams.dao.CompanyDao;
import com.dansmultipro.ams.dto.CreateResponseDto;
import com.dansmultipro.ams.dto.DeleteResponseDto;
import com.dansmultipro.ams.dto.UpdateResponseDto;
import com.dansmultipro.ams.dto.company.CompanyRequestDto;
import com.dansmultipro.ams.dto.company.CompanyResponseDto;
import com.dansmultipro.ams.dto.company.UpdateCompanyRequestDto;
import com.dansmultipro.ams.model.Company;
import com.dansmultipro.ams.service.CompanyService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class CompanyServiceImpl implements CompanyService {

    private final CompanyDao companyDao;

    public CompanyServiceImpl(CompanyDao companyDao) {
        this.companyDao = companyDao;
    }

    @Override
    public List<CompanyResponseDto> getAll() {
        List<CompanyResponseDto> result = companyDao.getAll().stream()
                .map(v -> new CompanyResponseDto(v.getId(), v.getName()))
                .toList();
        return result;
    }

    @Override
    public CompanyResponseDto getById(UUID id) {
        Company company = companyDao.getById(id).orElseThrow(
                () -> new RuntimeException("Company not found")
        );
        return new CompanyResponseDto(id, company.getName());
    }

    @Transactional
    @Override
    public CreateResponseDto insert(CompanyRequestDto data) {
        Company companyInsert = new Company();
        companyInsert.setId(UUID.randomUUID());
        companyInsert.setCreatedBy(UUID.randomUUID().toString());
        companyInsert.setCreatedAt(LocalDateTime.now());
        companyInsert.setName(data.getName());

        Company company = companyDao.insert(companyInsert);

        return new CreateResponseDto(company.getId(), "Saved");
    }

    @Transactional
    @Override
    public UpdateResponseDto update(UUID id, UpdateCompanyRequestDto data) {
        Company companyUpdate = companyDao.getById(id).orElseThrow(
                () -> new RuntimeException("Company not found")
        );
        companyUpdate.setName(data.getName());
        companyUpdate.setUpdatedBy(UUID.randomUUID().toString());
        companyUpdate.setUpdatedAt(LocalDateTime.now());

        companyDao.update(companyUpdate);

        return new UpdateResponseDto(companyUpdate.getVersion(), "Updated");
    }

    @Transactional
    @Override
    public DeleteResponseDto deleteById(UUID id) {
        Company company = companyDao.getById(id).orElseThrow(
                () -> new RuntimeException("Company not found")
        );

        companyDao.deleteById(company.getId());

        return new DeleteResponseDto("Deleted");
    }

}
