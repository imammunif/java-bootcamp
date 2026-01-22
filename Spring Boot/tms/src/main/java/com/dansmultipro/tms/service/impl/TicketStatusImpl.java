package com.dansmultipro.tms.service.impl;

import com.dansmultipro.tms.dto.ticketstatus.TicketStatusResponseDto;
import com.dansmultipro.tms.exception.NotFoundException;
import com.dansmultipro.tms.model.TicketStatus;
import com.dansmultipro.tms.repository.TicketStatusRepo;
import com.dansmultipro.tms.service.TicketStatusService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TicketStatusImpl extends BaseService implements TicketStatusService {

    private final TicketStatusRepo ticketStatusRepo;

    public TicketStatusImpl(TicketStatusRepo ticketStatusRepo) {
        this.ticketStatusRepo = ticketStatusRepo;
    }

    @Override
    public List<TicketStatusResponseDto> getAll() {
        List<TicketStatusResponseDto> result = ticketStatusRepo.findAll().stream()
                .map(v -> new TicketStatusResponseDto(v.getId(), v.getName(), v.getCode()))
                .toList();
        return result;
    }

    @Override
    public TicketStatusResponseDto getById(String id) {
        TicketStatus assetStatus = ticketStatusRepo.findById(UUID.fromString(id)).orElseThrow(
                () -> new NotFoundException("Status not found")
        );
        return new TicketStatusResponseDto(assetStatus.getId(), assetStatus.getName(), assetStatus.getCode());
    }

}
