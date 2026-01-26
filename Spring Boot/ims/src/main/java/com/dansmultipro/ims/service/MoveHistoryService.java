package com.dansmultipro.ims.service;

import com.dansmultipro.ims.dto.movehistory.MoveHistoryResponseDto;

import java.util.List;

public interface MoveHistoryService {

    List<MoveHistoryResponseDto> getAll();

    List<MoveHistoryResponseDto> getAllById(String productId);

}
