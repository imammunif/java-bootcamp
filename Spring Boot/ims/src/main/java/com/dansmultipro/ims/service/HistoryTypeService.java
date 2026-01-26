package com.dansmultipro.ims.service;

import com.dansmultipro.ims.dto.historytype.HistoryTypeResponseDto;

import java.util.List;

public interface HistoryTypeService {

    List<HistoryTypeResponseDto> getAll();

    HistoryTypeResponseDto getById(String id);

}
