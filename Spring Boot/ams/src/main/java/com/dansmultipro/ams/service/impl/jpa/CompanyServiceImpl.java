package com.dansmultipro.ams.service.impl.jpa;

import com.dansmultipro.ams.dto.CreateResponseDto;
import com.dansmultipro.ams.dto.DeleteResponseDto;
import com.dansmultipro.ams.dto.UpdateResponseDto;
import com.dansmultipro.ams.dto.company.CompanyRequestDto;
import com.dansmultipro.ams.dto.company.CompanyResponseDto;
import com.dansmultipro.ams.dto.company.UpdateCompanyRequestDto;
import com.dansmultipro.ams.model.Company;
import com.dansmultipro.ams.repository.CompanyRepo;
import com.dansmultipro.ams.service.CompanyService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Profile("jpa")
@Service
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepo companyRepo;

    @PersistenceContext
    private EntityManager em;

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
    public CreateResponseDto insert(CompanyRequestDto data) {
        Company companyInsert = new Company();
        companyInsert.setId(UUID.randomUUID());
        companyInsert.setCreatedBy(UUID.randomUUID());
        companyInsert.setCreatedAt(LocalDateTime.now());
        companyInsert.setName(data.getName());

        Company company = companyRepo.save(companyInsert);

        return new CreateResponseDto(company.getId(), "Saved");
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public UpdateResponseDto update(String id, UpdateCompanyRequestDto data) {
        Company companyUpdate = companyRepo.findById(UUID.fromString(id)).orElseThrow(
                () -> new RuntimeException("Company not found")
        );
        companyUpdate.setName(data.getName());
        companyUpdate.setUpdatedBy(UUID.randomUUID());
        companyUpdate.setUpdatedAt(LocalDateTime.now());

        companyRepo.save(companyUpdate);
        em.flush();

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
