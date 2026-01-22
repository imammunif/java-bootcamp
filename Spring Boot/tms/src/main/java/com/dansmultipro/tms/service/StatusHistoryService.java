package com.dansmultipro.tms.service;


import com.dansmultipro.tms.dto.statushistory.StatusHistoryResponseDto;

import java.util.List;

public interface StatusHistoryService {

    List<StatusHistoryResponseDto> getAll();

    StatusHistoryResponseDto getById(String id);

}