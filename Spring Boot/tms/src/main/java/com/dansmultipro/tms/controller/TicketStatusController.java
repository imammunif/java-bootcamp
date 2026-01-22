package com.dansmultipro.tms.controller;

import com.dansmultipro.tms.dto.ticketstatus.TicketStatusResponseDto;
import com.dansmultipro.tms.service.TicketStatusService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("ticket-statuses")
public class TicketStatusController {

    private final TicketStatusService ticketStatusService;

    public TicketStatusController(TicketStatusService ticketStatusService) {
        this.ticketStatusService = ticketStatusService;
    }

    @GetMapping
    public ResponseEntity<List<TicketStatusResponseDto>> getAllTicketStatus() {
        List<TicketStatusResponseDto> res = ticketStatusService.getAll();
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<TicketStatusResponseDto> getTicketStatusById(@PathVariable String id) {
        TicketStatusResponseDto res = ticketStatusService.getById(id);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

}
