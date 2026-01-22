package com.dansmultipro.tms.service.impl;

import com.dansmultipro.tms.dto.CreateResponseDto;
import com.dansmultipro.tms.dto.UpdateResponseDto;
import com.dansmultipro.tms.dto.ticket.CreateTicketRequestDto;
import com.dansmultipro.tms.dto.ticket.TicketResponseDto;
import com.dansmultipro.tms.dto.ticket.UpdateTicketRequestDto;
import com.dansmultipro.tms.exception.NotFoundException;
import com.dansmultipro.tms.model.Ticket;
import com.dansmultipro.tms.repository.TicketRepo;
import com.dansmultipro.tms.service.TicketService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class TicketServiceImpl extends BaseService implements TicketService {

    private final TicketRepo ticketRepo;

    public TicketServiceImpl(TicketRepo ticketRepo) {
        this.ticketRepo = ticketRepo;
    }

    @Override
    public List<TicketResponseDto> getAll() {
        List<Ticket> ticketList = ticketRepo.findAll();
        List<TicketResponseDto> ticketResponseDtoList = new ArrayList<>();
        for (Ticket v : ticketList) {
            TicketResponseDto responseDto = new TicketResponseDto(
                    v.getId(), v.getCode(), v.getTitle(), v.getDescription(),
                    v.getExpiredDate(), v.getStatus().getName(),
                    v.getCustomer().getFullName(),
                    v.getProduct().getName());
            ticketResponseDtoList.add(responseDto);
        }
        return ticketResponseDtoList;
    }

    @Override
    public TicketResponseDto getById(String id) {

        Ticket t = ticketRepo.findById(UUID.fromString(id)).orElseThrow(
                () -> new NotFoundException("Ticket not found")
        );
        return new TicketResponseDto(
                t.getId(), t.getCode(), t.getTitle(), t.getDescription(),
                t.getExpiredDate(), t.getStatus().getName(),
                t.getCustomer().getFullName(),
                t.getProduct().getName());
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
