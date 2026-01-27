package com.dansmultipro.ims.service;

import com.dansmultipro.ims.dto.CreateResponseDto;
import com.dansmultipro.ims.dto.PaginatedResponseDto;
import com.dansmultipro.ims.dto.movein.CreateMoveInRequestDto;
import com.dansmultipro.ims.dto.movein.MoveInResponseDto;
import com.dansmultipro.ims.dto.moveindetail.MoveInDetailResponseDto;

import java.util.List;

public interface MoveInService {

    PaginatedResponseDto<MoveInResponseDto> getAll(Integer page, Integer size);

    List<MoveInDetailResponseDto> getById(String id);

    CreateResponseDto create(CreateMoveInRequestDto requestDto);

}
