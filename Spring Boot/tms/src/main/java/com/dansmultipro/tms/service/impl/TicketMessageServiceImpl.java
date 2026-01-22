package com.dansmultipro.tms.service.impl;

import com.dansmultipro.tms.constant.TicketStatus;
import com.dansmultipro.tms.dto.CreateResponseDto;
import com.dansmultipro.tms.dto.UpdateResponseDto;
import com.dansmultipro.tms.dto.ticketmessage.CreateMessageRequestDto;
import com.dansmultipro.tms.dto.ticketmessage.MessageResponseDto;
import com.dansmultipro.tms.dto.ticketmessage.UpdateMessageRequestDto;
import com.dansmultipro.tms.exception.DataMissMatchException;
import com.dansmultipro.tms.exception.InvalidStatusException;
import com.dansmultipro.tms.exception.NotFoundException;
import com.dansmultipro.tms.model.Ticket;
import com.dansmultipro.tms.model.TicketMessage;
import com.dansmultipro.tms.model.User;
import com.dansmultipro.tms.repository.TicketMessageRepo;
import com.dansmultipro.tms.repository.TicketRepo;
import com.dansmultipro.tms.repository.UserRepo;
import com.dansmultipro.tms.service.TicketMessageService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TicketMessageServiceImpl extends BaseService implements TicketMessageService {

    private final TicketMessageRepo ticketMessageRepo;
    private final TicketRepo ticketRepo;
    private final UserRepo userRepo;

    public TicketMessageServiceImpl(TicketMessageRepo ticketMessageRepo, TicketRepo ticketRepo, UserRepo userRepo) {
        this.ticketMessageRepo = ticketMessageRepo;
        this.ticketRepo = ticketRepo;
        this.userRepo = userRepo;
    }

    @Override
    public List<MessageResponseDto> getAll() {
        List<MessageResponseDto> result = ticketMessageRepo.findAll().stream()
                .map(v -> new MessageResponseDto(v.getId(), v.getUser().getFullName(), v.getMessage()))
                .toList();
        return result;
    }

    @Override
    public CreateResponseDto create(CreateMessageRequestDto data) {
        Ticket ticket = ticketRepo.findById(UUID.fromString(data.getTicketId())).orElseThrow(
                () -> new NotFoundException("Ticket not found")
        );
        User user = userRepo.findById(UUID.fromString(data.getUserId())).orElseThrow(
                () -> new NotFoundException("User not found")
        );
        String statusCode = ticket.getStatus().getCode();
        if (statusCode.equals(TicketStatus.CLOSED.getCode()) || statusCode.equals(TicketStatus.RESOLVED.getCode())) {
            throw new InvalidStatusException("Ticket status is not valid");
        }
        TicketMessage newMessage = prepareForInsert(new TicketMessage());
        newMessage.setTicket(ticket);
        newMessage.setUser(user);
        newMessage.setMessage(data.getMessage());
        TicketMessage createdMessage = ticketMessageRepo.save(newMessage);

        return new CreateResponseDto(createdMessage.getId(), "Saved");
    }

    @Override
    public UpdateResponseDto update(String id, UpdateMessageRequestDto data) {
        TicketMessage message = ticketMessageRepo.findById(UUID.fromString(id)).orElseThrow(
                () -> new NotFoundException("Message not found")
        );
        if (!message.getVersion().equals(data.getVersion())) {
            throw new DataMissMatchException("Version not match");
        }
        Ticket ticket = message.getTicket();
        String statusCode = ticket.getStatus().getCode();
        if (statusCode.equals(TicketStatus.CLOSED.getCode()) || statusCode.equals(TicketStatus.RESOLVED.getCode())) {
            throw new InvalidStatusException("Ticket status is not valid");
        }
        TicketMessage updateMessage = prepareForUpdate(message);
        updateMessage.setMessage(data.getMessage());
        TicketMessage updatedMessage = ticketMessageRepo.saveAndFlush(updateMessage);

        return new UpdateResponseDto(updatedMessage.getVersion(), "Updated");
    }

}
