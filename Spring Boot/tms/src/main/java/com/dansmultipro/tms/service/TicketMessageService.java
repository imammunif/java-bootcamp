package com.dansmultipro.tms.service;


import com.dansmultipro.tms.dto.CreateResponseDto;
import com.dansmultipro.tms.dto.UpdateResponseDto;
import com.dansmultipro.tms.dto.ticketmessage.CreateMessageRequestDto;
import com.dansmultipro.tms.dto.ticketmessage.MessageResponseDto;
import com.dansmultipro.tms.dto.ticketmessage.UpdateMessageRequestDto;

import java.util.List;

public interface TicketMessageService {

    List<MessageResponseDto> getAll(String ticketId);

    CreateResponseDto create(String ticketId, CreateMessageRequestDto data);

    UpdateResponseDto update(String ticketId, String id, UpdateMessageRequestDto data);

}
