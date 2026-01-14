package com.dansmultipro.ams.service;

import com.dansmultipro.ams.dto.CreateResponseDto;
import com.dansmultipro.ams.dto.UpdateResponseDto;
import com.dansmultipro.ams.dto.assignment.AssignmentRequestDto;
import com.dansmultipro.ams.dto.assignment.AssignmentResponseDto;

import java.util.List;
import java.util.UUID;

public interface AssignmentService {

    List<AssignmentResponseDto> getAll();

    AssignmentResponseDto getById(UUID id);

    CreateResponseDto insert(AssignmentRequestDto data);

    UpdateResponseDto update(UUID id, List<String> detailIdList);

}
