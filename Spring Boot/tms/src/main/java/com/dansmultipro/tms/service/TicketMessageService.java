package com.dansmultipro.tms.service;


import com.dansmultipro.tms.dto.CreateResponseDto;
import com.dansmultipro.tms.dto.UpdateResponseDto;
import com.dansmultipro.tms.dto.ticketmessage.CreateMessageRequestDto;
import com.dansmultipro.tms.dto.ticketmessage.MessageResponseDto;
import com.dansmultipro.tms.dto.ticketmessage.UpdateMessageRequestDto;

import java.util.List;

public interface TicketMessageService {

    List<MessageResponseDto> getAll();

    CreateResponseDto create(CreateMessageRequestDto data);

    UpdateResponseDto update(String id, UpdateMessageRequestDto data);

}
