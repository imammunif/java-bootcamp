package com.dansmultipro.ims.service;

import com.dansmultipro.ims.dto.CreateResponseDto;
import com.dansmultipro.ims.dto.moveout.CreateMoveOutRequestDto;
import com.dansmultipro.ims.dto.moveout.MoveOutResponseDto;

import java.util.List;

public interface MoveOutService {

    List<MoveOutResponseDto> getAll();

    MoveOutResponseDto getById(String id);

    CreateResponseDto create(CreateMoveOutRequestDto requestDto);

}
