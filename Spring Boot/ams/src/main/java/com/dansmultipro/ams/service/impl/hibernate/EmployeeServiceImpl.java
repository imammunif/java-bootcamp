package com.dansmultipro.ams.service.impl.hibernate;

import com.dansmultipro.ams.dao.CompanyDao;
import com.dansmultipro.ams.dao.EmployeeDao;
import com.dansmultipro.ams.dto.CreateResponseDto;
import com.dansmultipro.ams.dto.DeleteResponseDto;
import com.dansmultipro.ams.dto.UpdateResponseDto;
import com.dansmultipro.ams.dto.employee.EmployeeRequestDto;
import com.dansmultipro.ams.dto.employee.EmployeeResponseDto;
import com.dansmultipro.ams.dto.employee.UpdateEmployeeRequestDto;
import com.dansmultipro.ams.exception.NotFoundException;
import com.dansmultipro.ams.model.Company;
import com.dansmultipro.ams.model.Employee;
import com.dansmultipro.ams.service.EmployeeService;
import com.dansmultipro.ams.service.impl.BaseService;
import jakarta.transaction.Transactional;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Profile("hibernate")
@Service
public class EmployeeServiceImpl extends BaseService implements EmployeeService {

    private final EmployeeDao employeeDao;
    private final CompanyDao companyDao;

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
                () -> new NotFoundException("Company not found")
        );
        Employee employeeNew = new Employee();
        Employee employeeInsert = prepareForInsert(employeeNew);
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
        Employee employee = employeeDao.getById(UUID.fromString(id)).orElseThrow(
                () -> new NotFoundException("Employee not found")
        );
        Employee employeeUpdate = prepareForUpdate(employee);
        employeeUpdate.setFullName(data.getFullName());
        employeeUpdate.setPhone(data.getPhone());
        employeeUpdate.setAddress(data.getAddress());
        employeeUpdate.setDateOfBirth(data.getDateOfBirth());

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
