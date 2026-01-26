package com.dansmultipro.ims.service;

import com.dansmultipro.ims.dto.CreateResponseDto;
import com.dansmultipro.ims.dto.moveoutdetail.CreateMoveOutDetailRequestDto;
import com.dansmultipro.ims.dto.moveoutdetail.MoveOutDetailResponseDto;

import java.util.List;

public interface MoveOutDetailService {

    List<MoveOutDetailResponseDto> getAllById(String moveOutId);

    CreateResponseDto create(String moveOutId, CreateMoveOutDetailRequestDto requestDto);

}
