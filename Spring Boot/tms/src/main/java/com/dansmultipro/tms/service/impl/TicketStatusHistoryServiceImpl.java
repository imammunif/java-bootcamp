package com.dansmultipro.tms.service.impl;

import com.dansmultipro.tms.dto.ticketstatushistory.StatusHistoryResponseDto;
import com.dansmultipro.tms.repository.TicketStatusHistoryRepo;
import com.dansmultipro.tms.service.TicketStatusHistoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketStatusHistoryServiceImpl extends BaseService implements TicketStatusHistoryService {

    private final TicketStatusHistoryRepo ticketStatusHistoryRepo;

    public TicketStatusHistoryServiceImpl(TicketStatusHistoryRepo ticketStatusHistoryRepo) {
        this.ticketStatusHistoryRepo = ticketStatusHistoryRepo;
    }

    @Override
    public List<StatusHistoryResponseDto> getAll() {
        return List.of();
    }

    @Override
    public StatusHistoryResponseDto getById(String id) {
        return null;
    }
}
