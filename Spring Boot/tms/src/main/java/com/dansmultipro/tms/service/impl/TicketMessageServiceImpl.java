package com.dansmultipro.tms.service.impl;

import com.dansmultipro.tms.config.RabbitMQConfig;
import com.dansmultipro.tms.constant.StatusCode;
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
import com.dansmultipro.tms.pojo.MailPoJo;
import com.dansmultipro.tms.repository.TicketMessageRepo;
import com.dansmultipro.tms.repository.TicketRepo;
import com.dansmultipro.tms.repository.UserRepo;
import com.dansmultipro.tms.service.TicketMessageService;
import com.dansmultipro.tms.util.MailUtil;
import jakarta.transaction.Transactional;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TicketMessageServiceImpl extends BaseService implements TicketMessageService {

    private final TicketMessageRepo ticketMessageRepo;
    private final TicketRepo ticketRepo;
    private final UserRepo userRepo;
    private final MailUtil mailUtil;
    private final RabbitTemplate rabbitTemplate;

    public TicketMessageServiceImpl(TicketMessageRepo ticketMessageRepo, TicketRepo ticketRepo, UserRepo userRepo, MailUtil mailUtil, RabbitTemplate rabbitTemplate) {
        this.ticketMessageRepo = ticketMessageRepo;
        this.ticketRepo = ticketRepo;
        this.userRepo = userRepo;
        this.mailUtil = mailUtil;
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public List<MessageResponseDto> getAll(String ticketId) {
        List<MessageResponseDto> result = ticketMessageRepo.findByTicketId(UUID.fromString(ticketId)).stream()
                .map(v -> new MessageResponseDto(v.getId(), v.getUser().getFullName(), v.getMessage()))
                .toList();
        return result;
    }

    @Override
    public CreateResponseDto create(String ticketId, CreateMessageRequestDto data) {
        Ticket ticket = ticketRepo.findById(UUID.fromString(ticketId)).orElseThrow(
                () -> new NotFoundException("Ticket not found")
        );
        User user = userRepo.findById(principalService.getPrincipal().getId()).orElseThrow(
                () -> new NotFoundException("User not found")
        );
        String statusCode = ticket.getStatus().getCode();
        if (statusCode.equals(StatusCode.CLOSED.getCode()) || statusCode.equals(StatusCode.RESOLVED.getCode())) {
            throw new InvalidStatusException("Ticket status is not valid");
        }
        TicketMessage newMessage = prepareForInsert(new TicketMessage());
        newMessage.setTicket(ticket);
        newMessage.setUser(user);
        newMessage.setMessage(data.getMessage());
        TicketMessage createdMessage = ticketMessageRepo.save(newMessage);

        MailPoJo mailPoJo = new MailPoJo(
                user.getEmail(),
                createdMessage.getMessage()
        );
        rabbitTemplate.convertAndSend(
                RabbitMQConfig.EMAIL_EX_MESSAGE,
                RabbitMQConfig.EMAIL_KEY_MESSAGE,
                mailPoJo
        );

        return new CreateResponseDto(createdMessage.getId(), "Saved");
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public UpdateResponseDto update(String ticketId, String id, UpdateMessageRequestDto data) {
        Ticket ticket = ticketRepo.findById(UUID.fromString(ticketId)).orElseThrow(
                () -> new NotFoundException("Ticket not found")
        );
        TicketMessage message = ticketMessageRepo.findById(UUID.fromString(id)).orElseThrow(
                () -> new NotFoundException("Message not found")
        );
        if (!message.getVersion().equals(data.getVersion())) {
            throw new DataMissMatchException("Version not match");
        }
        String statusCode = ticket.getStatus().getCode();
        if (statusCode.equals(StatusCode.CLOSED.getCode()) || statusCode.equals(StatusCode.RESOLVED.getCode())) {
            throw new InvalidStatusException("Ticket status is not valid");
        }
        TicketMessage updateMessage = prepareForUpdate(message);
        updateMessage.setMessage(data.getMessage());
        TicketMessage updatedMessage = ticketMessageRepo.saveAndFlush(updateMessage);

        return new UpdateResponseDto(updatedMessage.getVersion(), "Updated");
    }

    @RabbitListener(queues = RabbitMQConfig.EMAIL_QUEUE_MESSAGE)
    public void receiveEmailNotificationAssign(MailPoJo pojo) {
        mailUtil.send(
                pojo.getEmailAddress(),
                "Ticket has been replied",
                "Message :" + pojo.getEmailBody());
    }

}
