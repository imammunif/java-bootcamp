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

//@RestController
//@RequestMapping("ticket-messages")
public class TicketMessageController {

    private final TicketMessageService ticketMessageService;

    public TicketMessageController(TicketMessageService ticketMessageService) {
        this.ticketMessageService = ticketMessageService;
    }

    @GetMapping
    public ResponseEntity<List<MessageResponseDto>> getAllMessages() {
        List<MessageResponseDto> res = ticketMessageService.getAll();
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CreateResponseDto> createMessage(@RequestBody @Valid CreateMessageRequestDto requestDto) {
        CreateResponseDto res = ticketMessageService.create(requestDto);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<UpdateResponseDto> updateTicketMessage(@PathVariable String id, @RequestBody @Valid UpdateMessageRequestDto requestDto) {
        UpdateResponseDto res = ticketMessageService.update(id, requestDto);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

}
