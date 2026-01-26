package com.dansmultipro.ims.service;

import com.dansmultipro.ims.dto.CreateResponseDto;
import com.dansmultipro.ims.dto.moveindetail.CreateMoveInDetailRequestDto;
import com.dansmultipro.ims.dto.moveindetail.MoveInDetailResponseDto;

import java.util.List;

public interface MoveInDetailService {

    List<MoveInDetailResponseDto> getAllById(String moveInId);

    CreateResponseDto create(String moveInId, CreateMoveInDetailRequestDto requestDto);

}
