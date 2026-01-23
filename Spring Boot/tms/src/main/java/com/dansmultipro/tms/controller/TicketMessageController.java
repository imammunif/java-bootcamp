package com.dansmultipro.tms.controller;

import com.dansmultipro.tms.dto.CreateResponseDto;
import com.dansmultipro.tms.dto.UpdateResponseDto;
import com.dansmultipro.tms.dto.ticketmessage.CreateMessageRequestDto;
import com.dansmultipro.tms.dto.ticketmessage.MessageResponseDto;
import com.dansmultipro.tms.dto.ticketmessage.UpdateMessageRequestDto;
import com.dansmultipro.tms.service.TicketMessageService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("tickets")
public class TicketMessageController {

    private final TicketMessageService ticketMessageService;

    public TicketMessageController(TicketMessageService ticketMessageService) {
        this.ticketMessageService = ticketMessageService;
    }

    @GetMapping("{ticket_id}/messages")
    public ResponseEntity<List<MessageResponseDto>> getAllMessages(@PathVariable("ticket_id") String ticketId) {
        List<MessageResponseDto> res = ticketMessageService.getAll(ticketId);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PostMapping("{ticket_id}/messages")
    public ResponseEntity<CreateResponseDto> createMessage(
            @PathVariable("ticket_id") String ticketId,
            @RequestBody @Valid CreateMessageRequestDto requestDto
    ) {
        CreateResponseDto res = ticketMessageService.create(ticketId, requestDto);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @PutMapping("{ticket_id}/messages/{id}")
    public ResponseEntity<UpdateResponseDto> updateTicketMessage(
            @PathVariable("ticket_id") String ticketId,
            @PathVariable String id,
            @RequestBody @Valid UpdateMessageRequestDto requestDto
    ) {
        UpdateResponseDto res = ticketMessageService.update(ticketId, id, requestDto);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

}
