package com.dansmultipro.ams.controller;

import com.dansmultipro.ams.dto.CreateResponseDto;
import com.dansmultipro.ams.dto.DeleteResponseDto;
import com.dansmultipro.ams.dto.UpdateResponseDto;
import com.dansmultipro.ams.dto.employee.EmployeeRequestDto;
import com.dansmultipro.ams.dto.employee.EmployeeResponseDto;
import com.dansmultipro.ams.dto.employee.UpdateEmployeeRequestDto;
import com.dansmultipro.ams.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public ResponseEntity<List<EmployeeResponseDto>> getAllEmployees() {
        List<EmployeeResponseDto> res = employeeService.getAll();
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<EmployeeResponseDto> getEmployeeById(@PathVariable String id) {
        EmployeeResponseDto res = employeeService.getById(id);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CreateResponseDto> createEmployee(@RequestBody EmployeeRequestDto employee) {
        CreateResponseDto res = employeeService.insert(employee);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<UpdateResponseDto> updateEmployee(@PathVariable String id, @RequestBody UpdateEmployeeRequestDto employee) {
        UpdateResponseDto res = employeeService.update(id, employee);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<DeleteResponseDto> deleteEmployee(@PathVariable String id) {
        DeleteResponseDto res = employeeService.deleteById(id);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

}
