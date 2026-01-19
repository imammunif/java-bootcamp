package com.dansmultipro.ams.service.impl.jpa;

import com.dansmultipro.ams.dto.CreateResponseDto;
import com.dansmultipro.ams.dto.DeleteResponseDto;
import com.dansmultipro.ams.dto.UpdateResponseDto;
import com.dansmultipro.ams.dto.employee.EmployeeRequestDto;
import com.dansmultipro.ams.dto.employee.EmployeeResponseDto;
import com.dansmultipro.ams.dto.employee.UpdateEmployeeRequestDto;
import com.dansmultipro.ams.model.Company;
import com.dansmultipro.ams.model.Employee;
import com.dansmultipro.ams.repository.CompanyRepo;
import com.dansmultipro.ams.repository.EmployeeRepo;
import com.dansmultipro.ams.service.EmployeeService;
import com.dansmultipro.ams.service.impl.BaseService;
import jakarta.transaction.Transactional;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Profile("jpa")
@Service
public class EmployeeServiceImpl extends BaseService implements EmployeeService {

    private final EmployeeRepo employeeRepo;
    private final CompanyRepo companyRepo;

    public EmployeeServiceImpl(EmployeeRepo employeeRepo, CompanyRepo companyRepo) {
        this.employeeRepo = employeeRepo;
        this.companyRepo = companyRepo;
    }

    @Override
    public List<EmployeeResponseDto> getAll() {
        // id, fullName, phone, address, code, dateOfBirth
        List<EmployeeResponseDto> result = employeeRepo.findAll().stream()
                .map(v -> new EmployeeResponseDto(
                        v.getId(), v.getFullName(), v.getPhone(), v.getAddress(), v.getCode(), v.getCompany().getName(), v.getDateOfBirth()
                ))
                .toList();
        return result;
    }

    @Override
    public EmployeeResponseDto getById(String id) {
        Employee employee = employeeRepo.findById(UUID.fromString(id)).orElseThrow(
                () -> new RuntimeException("Employee not found")
        );
        return new EmployeeResponseDto(employee.getId(), employee.getFullName(), employee.getPhone(), employee.getAddress(), employee.getCode(), employee.getCompany().getName(), employee.getDateOfBirth());
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public CreateResponseDto insert(EmployeeRequestDto data) {
        Company company = companyRepo.findById(UUID.fromString(data.getCompanyId())).orElseThrow(
                () -> new RuntimeException("Company not found")
        );
        Employee employeeNew = new Employee();
        Employee employeeInsert = prepareForInsert(employeeNew);

        employeeInsert.setFullName(data.getFullName());
        employeeInsert.setCode(data.getCode());
        employeeInsert.setCompany(company);
        employeeInsert.setPhone(data.getPhone());
        employeeInsert.setAddress(data.getAddress());
        employeeInsert.setDateOfBirth(data.getDateOfBirth());

        Employee employee = employeeRepo.save(employeeInsert);

        return new CreateResponseDto(employee.getId(), "Saved");
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public UpdateResponseDto update(String id, UpdateEmployeeRequestDto data) {
        Employee employee = employeeRepo.findById(UUID.fromString(id)).orElseThrow(
                () -> new RuntimeException("Employee not found")
        );
        Employee employeeUpdate = prepareForUpdate(employee);
        employeeUpdate.setFullName(data.getFullName());
        employeeUpdate.setPhone(data.getPhone());
        employeeUpdate.setAddress(data.getAddress());
        employeeUpdate.setDateOfBirth(data.getDateOfBirth());

        employeeRepo.save(employeeUpdate);
        em.flush();

        return new UpdateResponseDto(employeeUpdate.getVersion(), "Updated");
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public DeleteResponseDto deleteById(String id) {
        Employee employee = employeeRepo.findById(UUID.fromString(id)).orElseThrow(
                () -> new RuntimeException("Employee not found")
        );

        employeeRepo.deleteById(employee.getId());

        return new DeleteResponseDto("Deleted");
    }

}
