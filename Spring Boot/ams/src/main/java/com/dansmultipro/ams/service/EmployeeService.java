package com.dansmultipro.ams.service;

import com.dansmultipro.ams.dto.CreateResponseDto;
import com.dansmultipro.ams.dto.DeleteResponseDto;
import com.dansmultipro.ams.dto.UpdateResponseDto;
import com.dansmultipro.ams.dto.employee.EmployeeRequestDto;
import com.dansmultipro.ams.dto.employee.EmployeeResponseDto;

import java.util.List;
import java.util.UUID;

public interface EmployeeService {

    List<EmployeeResponseDto> getAll();

    EmployeeResponseDto getById(UUID id);

    CreateResponseDto insert(EmployeeRequestDto data);

    UpdateResponseDto update(UUID id, EmployeeRequestDto data);

    DeleteResponseDto deleteById(UUID id);

}
