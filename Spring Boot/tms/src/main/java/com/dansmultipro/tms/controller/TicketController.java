package com.dansmultipro.tms.controller;

import com.dansmultipro.tms.dto.CreateResponseDto;
import com.dansmultipro.tms.dto.UpdateResponseDto;
import com.dansmultipro.tms.dto.ticket.CreateTicketRequestDto;
import com.dansmultipro.tms.dto.ticket.TicketResponseDto;
import com.dansmultipro.tms.dto.ticket.UpdateTicketRequestDto;
import com.dansmultipro.tms.service.TicketService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@RestController
//@RequestMapping("tickets")
public class TicketController {

    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping
    public ResponseEntity<List<TicketResponseDto>> getAllTickets() {
        List<TicketResponseDto> res = ticketService.getAll();
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<TicketResponseDto> getTicketById(@PathVariable String id) {
        TicketResponseDto res = ticketService.getById(id);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CreateResponseDto> createTicket(@RequestBody @Valid CreateTicketRequestDto requestDto) {
        CreateResponseDto res = ticketService.create(requestDto);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<UpdateResponseDto> updateTicket(@PathVariable String id, @RequestBody @Valid UpdateTicketRequestDto requestDto) {
        UpdateResponseDto res = ticketService.update(id, requestDto);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

}
