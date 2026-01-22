package com.dansmultipro.tms.service.impl;

import com.dansmultipro.tms.dto.CreateResponseDto;
import com.dansmultipro.tms.dto.UpdateResponseDto;
import com.dansmultipro.tms.dto.ticket.CreateTicketRequestDto;
import com.dansmultipro.tms.dto.ticket.TicketResponseDto;
import com.dansmultipro.tms.dto.ticket.UpdateTicketRequestDto;
import com.dansmultipro.tms.repository.TicketRepo;
import com.dansmultipro.tms.service.TicketService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketServiceImpl extends BaseService implements TicketService {

    private final TicketRepo ticketRepo;

    public TicketServiceImpl(TicketRepo ticketRepo) {
        this.ticketRepo = ticketRepo;
    }

    @Override
    public List<TicketResponseDto> getAll() {
        return List.of();
    }

    @Override
    public TicketResponseDto getById(String id) {
        return null;
    }

    @Override
    public CreateResponseDto create(CreateTicketRequestDto data) {
        return null;
    }

    @Override
    public UpdateResponseDto update(String id, UpdateTicketRequestDto data) {
        return null;
    }
}
