package com.dansmultipro.ams.service.impl.hibernate;

import com.dansmultipro.ams.dao.CompanyDao;
import com.dansmultipro.ams.dto.CreateResponseDto;
import com.dansmultipro.ams.dto.DeleteResponseDto;
import com.dansmultipro.ams.dto.UpdateResponseDto;
import com.dansmultipro.ams.dto.company.CompanyRequestDto;
import com.dansmultipro.ams.dto.company.CompanyResponseDto;
import com.dansmultipro.ams.dto.company.UpdateCompanyRequestDto;
import com.dansmultipro.ams.model.Company;
import com.dansmultipro.ams.service.CompanyService;
import com.dansmultipro.ams.service.impl.BaseService;
import jakarta.transaction.Transactional;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Profile("hibernate")
@Service
public class CompanyServiceImpl extends BaseService implements CompanyService {

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
    public CompanyResponseDto getById(String id) {
        Company company = companyDao.getById(UUID.fromString(id)).orElseThrow(
                () -> new RuntimeException("Company not found")
        );
        return new CompanyResponseDto(company.getId(), company.getName());
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public CreateResponseDto insert(CompanyRequestDto data) {
        Company companyNew = new Company();
        Company companyInsert = prepareForInsert(companyNew);
        companyInsert.setName(data.getName());

        Company company = companyDao.insert(companyInsert);

        return new CreateResponseDto(company.getId(), "Saved");
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public UpdateResponseDto update(String id, UpdateCompanyRequestDto data) {
        Company company = companyDao.getById(UUID.fromString(id)).orElseThrow(
                () -> new RuntimeException("Company not found")
        );
        Company companyUpdate = prepareForUpdate(company);
        companyUpdate.setName(data.getName());

        companyDao.update(companyUpdate);
        em.flush();

        return new UpdateResponseDto(companyUpdate.getVersion(), "Updated");
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public DeleteResponseDto deleteById(String id) {
        Company company = companyDao.getById(UUID.fromString(id)).orElseThrow(
                () -> new RuntimeException("Company not found")
        );

        companyDao.deleteById(company.getId());

        return new DeleteResponseDto("Deleted");
    }

}
