package com.dansmultipro.tms.controller;

import com.dansmultipro.tms.dto.ticketstatushistory.StatusHistoryResponseDto;
import com.dansmultipro.tms.service.TicketStatusHistoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("status-histories")
public class StatusHistoryController {

    private final TicketStatusHistoryService statusHistoryService;

    public StatusHistoryController(TicketStatusHistoryService statusHistoryService) {
        this.statusHistoryService = statusHistoryService;
    }

    @GetMapping
    public ResponseEntity<List<StatusHistoryResponseDto>> getAllStatusHistories() {
        List<StatusHistoryResponseDto> res = statusHistoryService.getAll();
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<StatusHistoryResponseDto> getStatusHistoryById(@PathVariable String id) {
        StatusHistoryResponseDto res = statusHistoryService.getById(id);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

}
