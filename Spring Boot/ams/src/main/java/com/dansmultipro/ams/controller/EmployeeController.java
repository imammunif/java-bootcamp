package com.dansmultipro.ams.controller;

import com.dansmultipro.ams.dto.CreateResponse;
import com.dansmultipro.ams.dto.DeleteResponse;
import com.dansmultipro.ams.dto.UpdateResponse;
import com.dansmultipro.ams.dto.employee.EmployeeRequest;
import com.dansmultipro.ams.dto.employee.EmployeeResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("companies")
public class EmployeeController {

    @GetMapping("{companyId}/employees")
    public List<EmployeeResponse> getAllEmployees(@PathVariable String companyId) {
        return null;
    }

    @GetMapping("{companyId}/employees/{id}")
    public EmployeeResponse getEmployeeById(@PathVariable String companyId, @PathVariable String id) {
        return null;
    }

    @PostMapping("{companyId}/employees")
    public CreateResponse createEmployee(@PathVariable String companyId, @RequestBody EmployeeRequest employee) {
        return null;
    }

    @PutMapping("{companyId}/employees/{id}")
    public UpdateResponse updateEmployee(@PathVariable String companyId, @PathVariable String id, @RequestBody EmployeeRequest employee) {
        return null;
    }

    @DeleteMapping("{companyId}/employees/{id}")
    public DeleteResponse deleteEmployee(@PathVariable String companyId, @PathVariable String id) {
        return null;
    }

}
