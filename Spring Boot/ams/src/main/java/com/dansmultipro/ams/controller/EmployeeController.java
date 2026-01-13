package com.dansmultipro.ams.controller;

import com.dansmultipro.ams.dto.CreateResponseDto;
import com.dansmultipro.ams.dto.DeleteResponseDto;
import com.dansmultipro.ams.dto.UpdateResponseDto;
import com.dansmultipro.ams.dto.employee.EmployeeRequestDto;
import com.dansmultipro.ams.dto.employee.EmployeeResponseDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("companies")
public class EmployeeController {

    @GetMapping("{companyId}/employees")
    public List<EmployeeResponseDto> getAllEmployees(@PathVariable String companyId) {
        return null;
    }

    @GetMapping("{companyId}/employees/{id}")
    public EmployeeResponseDto getEmployeeById(@PathVariable String companyId, @PathVariable String id) {
        return null;
    }

    @PostMapping("{companyId}/employees")
    public CreateResponseDto createEmployee(@PathVariable String companyId, @RequestBody EmployeeRequestDto employee) {
        return null;
    }

    @PutMapping("{companyId}/employees/{id}")
    public UpdateResponseDto updateEmployee(@PathVariable String companyId, @PathVariable String id, @RequestBody EmployeeRequestDto employee) {
        return null;
    }

    @DeleteMapping("{companyId}/employees/{id}")
    public DeleteResponseDto deleteEmployee(@PathVariable String companyId, @PathVariable String id) {
        return null;
    }

}
