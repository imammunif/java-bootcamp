package com.dansmultipro.ams.service.impl;

import com.dansmultipro.ams.dto.CreateResponseDto;
import com.dansmultipro.ams.dto.DeleteResponseDto;
import com.dansmultipro.ams.dto.UpdateResponseDto;
import com.dansmultipro.ams.dto.employee.EmployeeRequestDto;
import com.dansmultipro.ams.dto.employee.EmployeeResponseDto;
import com.dansmultipro.ams.dto.employee.UpdateEmployeeRequestDto;
import com.dansmultipro.ams.exception.DataIntegrityException;
import com.dansmultipro.ams.exception.DataMissMatchException;
import com.dansmultipro.ams.exception.NotFoundException;
import com.dansmultipro.ams.model.Company;
import com.dansmultipro.ams.model.Employee;
import com.dansmultipro.ams.repository.CompanyRepo;
import com.dansmultipro.ams.repository.EmployeeRepo;
import com.dansmultipro.ams.service.EmployeeService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

@Service
public class EmployeeServiceImpl extends BaseService implements EmployeeService {

    private final EmployeeRepo employeeRepo;
    private final CompanyRepo companyRepo;

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public EmployeeServiceImpl(EmployeeRepo employeeRepo, CompanyRepo companyRepo) {
        this.employeeRepo = employeeRepo;
        this.companyRepo = companyRepo;
    }

    @Override
    public List<EmployeeResponseDto> getAll() {
        List<EmployeeResponseDto> result = employeeRepo.findAll().stream()
                .map(v -> new EmployeeResponseDto(v.getId(), v.getFullName(), v.getPhone(), v.getAddress(), v.getCode(), v.getCompany().getName(), v.getDateOfBirth()))
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
    public CreateResponseDto insert(EmployeeRequestDto request) {
        Company company = companyRepo.findById(UUID.fromString(request.getCompanyId())).orElseThrow(
                () -> new NotFoundException("Company not found")
        );
        Employee employeeNew = new Employee();
        Employee employeeInsert = prepareForInsert(employeeNew);
        employeeInsert.setCompany(company);
        employeeInsert.setFullName(request.getFullName());
        employeeInsert.setAddress(request.getAddress());
        LocalDate dateOfBirth = LocalDate.parse(request.getDateOfBirth(), formatter);
        employeeInsert.setDateOfBirth(dateOfBirth);
        String requestCode = request.getCode();
        if (employeeRepo.findByCode(requestCode).isPresent()) {
            throw new DataIntegrityException("Code already exist");
        }
        employeeInsert.setCode(requestCode);
        String requestPhone = request.getPhone();
        if (employeeRepo.findByPhone(requestPhone).isPresent()) {
            throw new DataIntegrityException("Phone already exist");
        }
        employeeInsert.setPhone(requestPhone);
        Employee employee = employeeRepo.save(employeeInsert);

        return new CreateResponseDto(employee.getId(), "Saved");
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public UpdateResponseDto update(String id, UpdateEmployeeRequestDto request) {
        Employee employee = employeeRepo.findById(UUID.fromString(id)).orElseThrow(
                () -> new NotFoundException("Employee not found")
        );
        if (!employee.getVersion().toString().equals(request.getVersion())) {
            throw new DataMissMatchException("Version not match");
        }
        Employee employeeUpdate = prepareForUpdate(employee);
        employeeUpdate.setFullName(request.getFullName());
        employeeUpdate.setAddress(request.getAddress());
        LocalDate dateOfBirth = LocalDate.parse(request.getDateOfBirth(), formatter);
        employeeUpdate.setDateOfBirth(dateOfBirth);
        String requestPhone = request.getPhone();
        if (!employee.getPhone().equals(requestPhone)) {
            if (employeeRepo.findByPhone(requestPhone).isPresent()) {
                throw new DataIntegrityException("Phone already exist");
            }
        }
        employeeUpdate.setPhone(requestPhone);
        String requestCode = request.getCode();
        if (!employee.getCode().equals(requestCode)) {
            if (employeeRepo.findByCode(requestCode).isPresent()) {
                throw new DataIntegrityException("Code already exist");
            }
        }
        employeeUpdate.setCode(requestCode);
        employeeRepo.saveAndFlush(employeeUpdate);

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
