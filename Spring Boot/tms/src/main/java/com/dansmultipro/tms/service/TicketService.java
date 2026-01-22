package com.dansmultipro.tms.service;


import com.dansmultipro.tms.dto.CreateResponseDto;
import com.dansmultipro.tms.dto.UpdateResponseDto;
import com.dansmultipro.tms.dto.ticket.CreateTicketRequestDto;
import com.dansmultipro.tms.dto.ticket.TicketResponseDto;
import com.dansmultipro.tms.dto.ticket.UpdateTicketRequestDto;

import java.util.List;

public interface TicketService {

    List<TicketResponseDto> getAll();

    TicketResponseDto getById(String id);

    CreateResponseDto create(CreateTicketRequestDto data);

    UpdateResponseDto update(String id, String statusCode, UpdateTicketRequestDto data);

}
