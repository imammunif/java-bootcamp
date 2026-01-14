package com.dansmultipro.ams.service;

import com.dansmultipro.ams.dto.UpdateResponseDto;
import com.dansmultipro.ams.dto.assignment.AssignmentCreateResponseDto;
import com.dansmultipro.ams.dto.assignment.AssignmentRequestDto;
import com.dansmultipro.ams.dto.assignment.AssignmentResponseDto;

import java.util.List;

public interface AssignmentService {

    List<AssignmentResponseDto> getAll();

    AssignmentResponseDto getById(String id);

    AssignmentCreateResponseDto insert(AssignmentRequestDto data);

    UpdateResponseDto update(String id, List<String> detailIdList);

}
