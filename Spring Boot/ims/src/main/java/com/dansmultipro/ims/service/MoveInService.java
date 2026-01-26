package com.dansmultipro.ims.service;

import com.dansmultipro.ims.dto.CreateResponseDto;
import com.dansmultipro.ims.dto.movein.CreateMoveInRequestDto;
import com.dansmultipro.ims.dto.movein.MoveInResponseDto;

import java.util.List;

public interface MoveInService {

    List<MoveInResponseDto> getAll();

    MoveInResponseDto getById(String id);

    CreateResponseDto create(CreateMoveInRequestDto requestDto);

}
