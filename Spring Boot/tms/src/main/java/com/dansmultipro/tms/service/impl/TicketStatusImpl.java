package com.dansmultipro.tms.service.impl;

import com.dansmultipro.tms.dto.ticketstatus.TicketStatusResponseDto;
import com.dansmultipro.tms.repository.TicketStatusRepo;
import com.dansmultipro.tms.service.TicketStatusService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketStatusImpl extends BaseService implements TicketStatusService {

    private final TicketStatusRepo ticketStatusRepo;

    public TicketStatusImpl(TicketStatusRepo ticketStatusRepo) {
        this.ticketStatusRepo = ticketStatusRepo;
    }

    @Override
    public List<TicketStatusResponseDto> getAll() {
        return List.of();
    }

    @Override
    public TicketStatusResponseDto getById(String id) {
        return null;
    }
}
