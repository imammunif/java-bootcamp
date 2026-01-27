package com.dansmultipro.ims.service;

import com.dansmultipro.ims.dto.CreateResponseDto;
import com.dansmultipro.ims.dto.PaginatedResponseDto;
import com.dansmultipro.ims.dto.moveout.CreateMoveOutRequestDto;
import com.dansmultipro.ims.dto.moveout.MoveOutResponseDto;
import com.dansmultipro.ims.dto.moveoutdetail.MoveOutDetailResponseDto;

import java.util.List;

public interface MoveOutService {

    PaginatedResponseDto<MoveOutResponseDto> getAll(Integer page, Integer size);

    List<MoveOutDetailResponseDto> getById(String id);

    CreateResponseDto create(CreateMoveOutRequestDto requestDto);

}
