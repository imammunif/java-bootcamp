package com.dansmultipro.ams.service.impl;

import com.dansmultipro.ams.dao.CompanyDao;
import com.dansmultipro.ams.dao.EmployeeDao;
import com.dansmultipro.ams.dto.CreateResponseDto;
import com.dansmultipro.ams.dto.DeleteResponseDto;
import com.dansmultipro.ams.dto.UpdateResponseDto;
import com.dansmultipro.ams.dto.employee.EmployeeRequestDto;
import com.dansmultipro.ams.dto.employee.EmployeeResponseDto;
import com.dansmultipro.ams.dto.employee.UpdateEmployeeRequestDto;
import com.dansmultipro.ams.model.Company;
import com.dansmultipro.ams.model.Employee;
import com.dansmultipro.ams.service.EmployeeService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeDao employeeDao;
    private final CompanyDao companyDao;

    @PersistenceContext
    private EntityManager em;

    public EmployeeServiceImpl(EmployeeDao employeeDao, CompanyDao companyDao) {
        this.employeeDao = employeeDao;
        this.companyDao = companyDao;
    }

    @Override
    public List<EmployeeResponseDto> getAll() {
        // id, fullName, phone, address, code, dateOfBirth
        List<EmployeeResponseDto> result = employeeDao.getAll().stream()
                .map(v -> new EmployeeResponseDto(
                        v.getId(), v.getFullName(), v.getPhone(), v.getAddress(), v.getCode(), v.getCompany().getName(), v.getDateOfBirth()
                ))
                .toList();
        return result;
    }

    @Override
    public EmployeeResponseDto getById(String id) {
        Employee employee = employeeDao.getById(UUID.fromString(id)).orElseThrow(
                () -> new RuntimeException("Employee not found")
        );
        return new EmployeeResponseDto(employee.getId(), employee.getFullName(), employee.getPhone(), employee.getAddress(), employee.getCode(), employee.getCompany().getName(), employee.getDateOfBirth());
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public CreateResponseDto insert(EmployeeRequestDto data) {
        Company company = companyDao.getById(UUID.fromString(data.getCompanyId())).orElseThrow(
                () -> new RuntimeException("Company not found")
        );
        Employee employeeInsert = new Employee();
        employeeInsert.setId(UUID.randomUUID());
        employeeInsert.setCreatedBy(UUID.randomUUID().toString());
        employeeInsert.setCreatedAt(LocalDateTime.now());
        employeeInsert.setFullName(data.getFullName());
        employeeInsert.setCode(data.getCode());
        employeeInsert.setCompany(company);
        employeeInsert.setPhone(data.getPhone());
        employeeInsert.setAddress(data.getAddress());
        employeeInsert.setDateOfBirth(data.getDateOfBirth());

        Employee employee = employeeDao.insert(employeeInsert);

        return new CreateResponseDto(employee.getId(), "Saved");
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public UpdateResponseDto update(String id, UpdateEmployeeRequestDto data) {
        Employee employeeUpdate = employeeDao.getById(UUID.fromString(id)).orElseThrow(
                () -> new RuntimeException("Employee not found")
        );
        employeeUpdate.setFullName(data.getFullName());
        employeeUpdate.setPhone(data.getPhone());
        employeeUpdate.setAddress(data.getAddress());
        employeeUpdate.setDateOfBirth(data.getDateOfBirth());
        employeeUpdate.setUpdatedBy(UUID.randomUUID().toString());
        employeeUpdate.setUpdatedAt(LocalDateTime.now());

        employeeDao.update(employeeUpdate);
        em.flush();

        return new UpdateResponseDto(employeeUpdate.getVersion(), "Updated");
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public DeleteResponseDto deleteById(String id) {
        Employee employee = employeeDao.getById(UUID.fromString(id)).orElseThrow(
                () -> new RuntimeException("Employee not found")
        );

        employeeDao.deleteById(employee.getId());

        return new DeleteResponseDto("Deleted");
    }

}
