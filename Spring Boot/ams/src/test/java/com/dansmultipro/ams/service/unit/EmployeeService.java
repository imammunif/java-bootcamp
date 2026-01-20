package com.dansmultipro.ams.service.unit;

import com.dansmultipro.ams.dto.employee.EmployeeRequestDto;
import com.dansmultipro.ams.dto.employee.UpdateEmployeeRequestDto;
import com.dansmultipro.ams.model.Company;
import com.dansmultipro.ams.model.Employee;
import com.dansmultipro.ams.repository.CompanyRepo;
import com.dansmultipro.ams.repository.EmployeeRepo;
import com.dansmultipro.ams.service.impl.EmployeeServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
public class EmployeeService {

    @Mock
    private EmployeeRepo employeeRepo;

    @Mock
    private CompanyRepo companyRepo;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @Test
    public void shouldCreated_whenDataValid() {
        var dto = new EmployeeRequestDto();
        dto.setCompanyId(UUID.randomUUID().toString());
        dto.setFullName("John Doe");
        dto.setCode("EMP01");
        dto.setDateOfBirth("2026-01-20");

        var employeeSaved = new Employee();
        var id = UUID.randomUUID();
        employeeSaved.setId(id);

        Mockito.when(companyRepo.findById(Mockito.any())).thenReturn(Optional.of(new Company()));
        Mockito.when(employeeRepo.save(Mockito.any())).thenReturn(employeeSaved);

        var result = employeeService.insert(dto);

        Assertions.assertEquals(id, result.getId());
        Mockito.verify(companyRepo, Mockito.atLeast(1)).findById(Mockito.any());
        Mockito.verify(employeeRepo, Mockito.atLeast(1)).save(Mockito.any());
    }

    @Test
    public void shouldReturnData_whenIdValid() {
        var company = new Company();
        company.setName("Dans Academy");

        var id = UUID.randomUUID();
        var employeeSaved = new Employee();
        employeeSaved.setId(id);
        employeeSaved.setCode("EMP01");
        employeeSaved.setCompany(company);

        Mockito.when(employeeRepo.findById(Mockito.any())).thenReturn(Optional.of(employeeSaved));

        var result = employeeService.getById(id.toString());

        Assertions.assertEquals("EMP01", result.getCode());
        Mockito.verify(employeeRepo, Mockito.atLeast(1)).findById(id);
    }

    @Test
    public void shouldUpdateData_whenVersionValid() {
        var company = new Company();
        company.setName("Dans Academy");

        var id = UUID.randomUUID();
        var employee = new Employee();
        employee.setId(id);
        employee.setFullName("John Doe");
        employee.setCompany(company);
        employee.setPhone("081257575757");
        employee.setCode("EMP01");
        employee.setVersion(0);

        var dto = new UpdateEmployeeRequestDto();
        dto.setFullName("John Patrick Doe");
        dto.setDateOfBirth("2026-01-20");
        dto.setVersion(0);

        var updatedEmployee = new Employee();
        updatedEmployee.setId(id);
        updatedEmployee.setFullName(dto.getFullName());
        updatedEmployee.setCompany(company);
        updatedEmployee.setPhone("081257575757");
        updatedEmployee.setCode("EMP01");
        updatedEmployee.setVersion(1);

        Mockito.when(employeeRepo.findById(Mockito.any())).thenReturn(Optional.of(employee));
        Mockito.when(employeeRepo.saveAndFlush(Mockito.any())).thenReturn(updatedEmployee);

        var result = employeeService.update(id.toString(), dto);

        Assertions.assertEquals(1, result.getVersion());
        Mockito.verify(employeeRepo, Mockito.atLeast(1)).findById(Mockito.any());
        Mockito.verify(employeeRepo, Mockito.atLeast(1)).saveAndFlush(Mockito.any());
    }

    @Test
    public void shouldReturnAll_whenExist() {
        var company = new Company();
        company.setName("Dans Academy");

        var empl1 = new Employee();
        empl1.setId(UUID.randomUUID());
        empl1.setCode("EMP01");
        empl1.setCompany(company);

        var empl2 = new Employee();
        empl2.setId(UUID.randomUUID());
        empl2.setCode("EMP02");
        empl2.setCompany(company);

        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(empl1);
        employeeList.add(empl2);

        Mockito.when(employeeRepo.findAll()).thenReturn(employeeList);

        var result = employeeService.getAll();

        Assertions.assertEquals(employeeList.size(), result.size());
        Assertions.assertEquals("EMP01", result.getFirst().getCode());
        Mockito.verify(employeeRepo, Mockito.atLeast(1)).findAll();
    }

}
