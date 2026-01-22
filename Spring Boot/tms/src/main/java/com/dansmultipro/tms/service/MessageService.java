package com.dansmultipro.tms.service;


import com.dansmultipro.tms.dto.CreateResponseDto;
import com.dansmultipro.tms.dto.UpdateResponseDto;
import com.dansmultipro.tms.dto.message.UpdateMessageRequestDto;
import com.dansmultipro.tms.dto.ticket.CreateTicketRequestDto;
import com.dansmultipro.tms.dto.ticket.TicketResponseDto;

import java.util.List;

public interface MessageService {

    List<TicketResponseDto> getAll();

    CreateResponseDto create(CreateTicketRequestDto data);

    UpdateResponseDto update(String id, UpdateMessageRequestDto data);

}
