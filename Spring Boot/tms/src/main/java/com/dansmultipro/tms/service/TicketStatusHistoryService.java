package com.dansmultipro.tms.service;


import com.dansmultipro.tms.dto.ticketstatushistory.StatusHistoryResponseDto;

import java.util.List;

public interface TicketStatusHistoryService {

    List<StatusHistoryResponseDto> getAll();

    StatusHistoryResponseDto getById(String id);

}