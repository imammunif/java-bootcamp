package com.dansmultipro.tms.service.impl;

import com.dansmultipro.tms.dto.CreateResponseDto;
import com.dansmultipro.tms.dto.UpdateResponseDto;
import com.dansmultipro.tms.dto.ticketmessage.CreateMessageRequestDto;
import com.dansmultipro.tms.dto.ticketmessage.MessageResponseDto;
import com.dansmultipro.tms.dto.ticketmessage.UpdateMessageRequestDto;
import com.dansmultipro.tms.repository.TicketMessageRepo;
import com.dansmultipro.tms.service.TicketMessageService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketMessageServiceImpl extends BaseService implements TicketMessageService {

    private final TicketMessageRepo ticketMessageRepo;

    public TicketMessageServiceImpl(TicketMessageRepo ticketMessageRepo) {
        this.ticketMessageRepo = ticketMessageRepo;
    }

    @Override
    public List<MessageResponseDto> getAll() {
        List<MessageResponseDto> result = ticketMessageRepo.findAll().stream()
                .map(v -> new MessageResponseDto(v.getId(), v.getUser().getFullName(), v.getMessage()))
                .toList();
        return result;
    }

    @Override
    public CreateResponseDto create(CreateMessageRequestDto data) {
        return null;
    }

    @Override
    public UpdateResponseDto update(String id, UpdateMessageRequestDto data) {
        return null;
    }

}
