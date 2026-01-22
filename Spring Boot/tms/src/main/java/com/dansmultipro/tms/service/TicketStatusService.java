package com.dansmultipro.tms.service;

import com.dansmultipro.tms.dto.ticketstatus.TicketStatusResponseDto;

import java.util.List;

public interface TicketStatusService {

    List<TicketStatusResponseDto> getAll();

    TicketStatusResponseDto getById(String id);

}