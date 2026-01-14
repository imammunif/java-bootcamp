package com.dansmultipro.ams.service;

import com.dansmultipro.ams.dto.CreateResponseDto;
import com.dansmultipro.ams.dto.DeleteResponseDto;
import com.dansmultipro.ams.dto.UpdateResponseDto;
import com.dansmultipro.ams.dto.employee.EmployeeRequestDto;
import com.dansmultipro.ams.dto.employee.EmployeeResponseDto;
import com.dansmultipro.ams.dto.employee.UpdateEmployeeRequestDto;

import java.util.List;
import java.util.UUID;

public interface EmployeeService {

    List<EmployeeResponseDto> getAll();

    EmployeeResponseDto getById(String id);

    CreateResponseDto insert(EmployeeRequestDto data);

    UpdateResponseDto update(String id, UpdateEmployeeRequestDto data);

    DeleteResponseDto deleteById(String id);

}
