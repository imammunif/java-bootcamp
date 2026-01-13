package com.dansmultipro.ams.service.impl;

import com.dansmultipro.ams.dao.EmployeeDao;
import com.dansmultipro.ams.dto.CreateResponseDto;
import com.dansmultipro.ams.dto.DeleteResponseDto;
import com.dansmultipro.ams.dto.UpdateResponseDto;
import com.dansmultipro.ams.dto.employee.EmployeeRequestDto;
import com.dansmultipro.ams.dto.employee.EmployeeResponseDto;
import com.dansmultipro.ams.model.Employee;
import com.dansmultipro.ams.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeDao employeeDao;

    public EmployeeServiceImpl(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    @Override
    public List<EmployeeResponseDto> getAll() {
        // id, fullName, phone, address, code, dateOfBirth
        List<EmployeeResponseDto> result = employeeDao.getAll().stream()
                .map(v -> new EmployeeResponseDto(
                        v.getId(), v.getName(), v.getPhone(),
                        v.getAddress(), v.getCode(), v.getDateOfBirth()
                ))
                .toList();
        return result;
    }

    @Override
    public EmployeeResponseDto getById(UUID id) {
        Employee employee = employeeDao.getById(id).orElseThrow(
                () -> new RuntimeException("Employee not found")
        );
        return new EmployeeResponseDto(id, employee.getName(),
                employee.getPhone(), employee.getAddress(), employee.getCode(), employee.getDateOfBirth());
    }

    @Override
    public CreateResponseDto insert(EmployeeRequestDto data) {
        Employee employeeInsert = new Employee();
        employeeInsert.setId(UUID.randomUUID());
        employeeInsert.setCreatedBy(UUID.randomUUID().toString());
        employeeInsert.setCreatedAt(LocalDateTime.now());
        employeeInsert.setName(data.getFullName());
        employeeInsert.setPhone(data.getPhone());
        employeeInsert.setAddress(data.getAddress());
        employeeInsert.setDateOfBirth(data.getDateOfBirth());

        Employee employee = employeeDao.insert(employeeInsert);

        return new CreateResponseDto(employee.getId(), "Saved");
    }

    @Override
    public UpdateResponseDto update(UUID id, EmployeeRequestDto data) {
        Employee employeeUpdate = employeeDao.getById(id).orElseThrow(
                () -> new RuntimeException("Employee not found")
        );
        employeeUpdate.setName(data.getFullName());
        employeeUpdate.setPhone(data.getPhone());
        employeeUpdate.setAddress(data.getAddress());
        employeeUpdate.setDateOfBirth(data.getDateOfBirth());
        employeeUpdate.setUpdatedBy(UUID.randomUUID().toString());
        employeeUpdate.setUpdatedAt(LocalDateTime.now());

        employeeDao.update(employeeUpdate);

        return new UpdateResponseDto(employeeUpdate.getVersion(), "Updated");
    }

    @Override
    public DeleteResponseDto deleteById(UUID id) {
        Employee employee = employeeDao.getById(id).orElseThrow(
                () -> new RuntimeException("Employee not found")
        );

        employeeDao.deleteById(employee.getId());

        return new DeleteResponseDto("Deleted");
    }

}
