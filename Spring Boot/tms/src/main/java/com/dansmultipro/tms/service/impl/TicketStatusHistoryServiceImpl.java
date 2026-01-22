package com.dansmultipro.tms.service.impl;

import com.dansmultipro.tms.dto.ticketstatushistory.StatusHistoryResponseDto;
import com.dansmultipro.tms.exception.NotFoundException;
import com.dansmultipro.tms.model.TicketStatusHistory;
import com.dansmultipro.tms.repository.TicketStatusHistoryRepo;
import com.dansmultipro.tms.service.TicketStatusHistoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TicketStatusHistoryServiceImpl extends BaseService implements TicketStatusHistoryService {

    private final TicketStatusHistoryRepo ticketStatusHistoryRepo;

    public TicketStatusHistoryServiceImpl(TicketStatusHistoryRepo ticketStatusHistoryRepo) {
        this.ticketStatusHistoryRepo = ticketStatusHistoryRepo;
    }

    @Override
    public List<StatusHistoryResponseDto> getAll() {
        List<StatusHistoryResponseDto> result = ticketStatusHistoryRepo.findAll().stream()
                .map(v -> new StatusHistoryResponseDto(v.getId(), v.getStatus().getName(), v.getTicket().getCode()))
                .toList();
        return result;
    }

    @Override
    public StatusHistoryResponseDto getById(String id) {
        TicketStatusHistory ticketStatusHistory = ticketStatusHistoryRepo.findById(UUID.fromString(id)).orElseThrow(
                () -> new NotFoundException("Ticket status history not found")
        );
        return new StatusHistoryResponseDto(ticketStatusHistory.getId(), ticketStatusHistory.getStatus().getName(), ticketStatusHistory.getTicket().getCode());
    }

}
