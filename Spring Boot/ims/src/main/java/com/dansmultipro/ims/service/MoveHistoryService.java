package com.dansmultipro.ims.service;

import com.dansmultipro.ims.dto.PaginatedResponseDto;
import com.dansmultipro.ims.dto.movehistory.MoveHistoryResponseDto;

import java.util.List;

public interface MoveHistoryService {

    PaginatedResponseDto<MoveHistoryResponseDto> getAll(Integer page, Integer size);

    List<MoveHistoryResponseDto> getAllById(String productId);

}
