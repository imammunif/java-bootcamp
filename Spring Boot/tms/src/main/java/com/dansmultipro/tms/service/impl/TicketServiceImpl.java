package com.dansmultipro.tms.service.impl;

import com.dansmultipro.tms.config.RabbitMQConfig;
import com.dansmultipro.tms.constant.StatusCode;
import com.dansmultipro.tms.dto.CreateResponseDto;
import com.dansmultipro.tms.dto.UpdateResponseDto;
import com.dansmultipro.tms.dto.ticket.CreateTicketRequestDto;
import com.dansmultipro.tms.dto.ticket.TicketResponseDto;
import com.dansmultipro.tms.dto.ticket.UpdateTicketRequestDto;
import com.dansmultipro.tms.exception.DataMissMatchException;
import com.dansmultipro.tms.exception.InvalidStatusException;
import com.dansmultipro.tms.exception.NotFoundException;
import com.dansmultipro.tms.model.*;
import com.dansmultipro.tms.pojo.MailPoJo;
import com.dansmultipro.tms.repository.*;
import com.dansmultipro.tms.service.TicketService;
import com.dansmultipro.tms.util.MailUtil;
import com.dansmultipro.tms.util.RandomGenerator;
import jakarta.transaction.Transactional;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class TicketServiceImpl extends BaseService implements TicketService {

    private final TicketRepo ticketRepo;
    private final TicketStatusRepo ticketStatusRepo;
    private final TicketStatusHistoryRepo ticketStatusHistoryRepo;
    private final UserRepo userRepo;
    private final ProductRepo productRepo;
    private final PicCustomerRepo picCustomerRepo;
    private final MailUtil mailUtil;
    private final RabbitTemplate rabbitTemplate;

    public TicketServiceImpl(TicketRepo ticketRepo, TicketStatusHistoryRepo ticketStatusHistoryRepo, UserRepo userRepo, TicketStatusRepo ticketStatusRepo, ProductRepo productRepo, PicCustomerRepo picCustomerRepo, MailUtil mailUtil, RabbitTemplate rabbitTemplate) {
        this.ticketRepo = ticketRepo;
        this.ticketStatusHistoryRepo = ticketStatusHistoryRepo;
        this.userRepo = userRepo;
        this.ticketStatusRepo = ticketStatusRepo;
        this.productRepo = productRepo;
        this.picCustomerRepo = picCustomerRepo;
        this.mailUtil = mailUtil;
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    @Cacheable(value = "tickets", key = "'all'")
    public List<TicketResponseDto> getAll() {
        List<Ticket> ticketList = ticketRepo.findAll();
        List<TicketResponseDto> ticketResponseDtoList = new ArrayList<>();
        for (Ticket v : ticketList) {
            TicketResponseDto responseDto = new TicketResponseDto(
                    v.getId().toString(), v.getCode(), v.getTitle(), v.getDescription(),
                    v.getExpiredDate().toString(), v.getStatus().getName(),
                    v.getCustomer().getFullName(),
                    v.getProduct().getName());
            ticketResponseDtoList.add(responseDto);
        }
        return ticketResponseDtoList;
    }

    @Override
    @Cacheable(value = "tickets", key = "#id")
    public TicketResponseDto getById(String id) {

        Ticket t = ticketRepo.findById(UUID.fromString(id)).orElseThrow(
                () -> new NotFoundException("Ticket not found")
        );
        return new TicketResponseDto(
                t.getId().toString(), t.getCode(), t.getTitle(), t.getDescription(),
                t.getExpiredDate().toString(), t.getStatus().getName(),
                t.getCustomer().getFullName(),
                t.getProduct().getName());
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    @CacheEvict(value = "tickets", allEntries = true)
    public CreateResponseDto create(CreateTicketRequestDto data) {
        User customer = userRepo.findById(principalService.getPrincipal().getId()).orElseThrow(
                () -> new NotFoundException("Customer not found")
        );
        Product product = productRepo.findById(UUID.fromString(data.getProductId())).orElseThrow(
                () -> new NotFoundException("Product not found")
        );
        TicketStatus status = ticketStatusRepo.findByCode(StatusCode.OPEN.getCode()).orElseThrow(
                () -> new NotFoundException("Ticket status not found")
        );
        PicCustomer picCustomer = picCustomerRepo.findByCustomerId(customer.getId()).orElseThrow(
                () -> new NotFoundException("No PIC-Customer relation found")
        );

        Ticket newTicket = prepareForInsert(new Ticket());
        newTicket.setCode(RandomGenerator.randomizeCode(5));
        newTicket.setStatus(status);
        newTicket.setExpiredDate(LocalDate.now().plusDays(3));
        newTicket.setCustomer(customer);
        newTicket.setProduct(product);
        newTicket.setTitle(data.getTitle());
        newTicket.setDescription(data.getDescription());
        Ticket createdTicket = ticketRepo.save(newTicket);

        MailPoJo mailPoJo = new MailPoJo(
                picCustomer.getPic().getEmail(),
                createdTicket.getCode()
        );
        rabbitTemplate.convertAndSend(
                RabbitMQConfig.EMAIL_EX_TICKET,
                RabbitMQConfig.EMAIL_KEY_TICKET,
                mailPoJo
        );

        return new CreateResponseDto(createdTicket.getId(), "Saved");
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public UpdateResponseDto update(String id, String newStatus, UpdateTicketRequestDto data) {
        Ticket ticket = ticketRepo.findById(UUID.fromString(id)).orElseThrow(
                () -> new NotFoundException("Ticket not found")
        );
        if (!ticket.getVersion().equals(data.getVersion())) {
            throw new DataMissMatchException("Version not match");
        }
        TicketStatus ticketStatus = ticketStatusRepo.findByCode(newStatus).orElseThrow(
                () -> new InvalidStatusException("Status is not valid")
        );

        Ticket updateTicket = prepareForUpdate(ticket);
        String currentStatus = ticket.getStatus().getCode();

        if (newStatus.equals(StatusCode.OPEN.getCode()) || newStatus.equals(StatusCode.REOPEN.getCode())) {
            if (currentStatus.equals(StatusCode.OPEN.getCode()) || currentStatus.equals(StatusCode.REOPEN.getCode())) {
                throw new InvalidStatusException("Ticket is already open");
            } else {
                updateTicket.setStatus(ticketStatus);
            }
        }
        if (newStatus.equals(StatusCode.RESOLVED.getCode())) {
            if (currentStatus.equals(StatusCode.RESOLVED.getCode())) {
                throw new InvalidStatusException("Ticket is already resolved");
            } else if (currentStatus.equals(StatusCode.CLOSED.getCode())) {
                throw new InvalidStatusException("Ticket can't be resolved, already closed");
            } else {
                updateTicket.setStatus(ticketStatus);
            }
        }
        if (newStatus.equals(StatusCode.CLOSED.getCode())) {
            if (!currentStatus.equals(StatusCode.RESOLVED.getCode())) {
                throw new InvalidStatusException("Only resolved ticket can be closed");
            } else {
                updateTicket.setStatus(ticketStatus);
            }
        }
        Ticket updatedTicket = ticketRepo.saveAndFlush(updateTicket);

        TicketStatusHistory newStatusHistory = prepareForInsert(new TicketStatusHistory());
        newStatusHistory.setStatus(ticketStatus);
        newStatusHistory.setTicket(ticket);
        ticketStatusHistoryRepo.save(newStatusHistory);

        return new UpdateResponseDto(updatedTicket.getVersion(), "Updated");
    }

    @RabbitListener(queues = RabbitMQConfig.EMAIL_QUEUE_TICKET)
    public void receiveEmailNotificationAssign(MailPoJo pojo) {
        mailUtil.send(
                pojo.getEmailAddress(),
                "New Ticket Created",
                "Ticket " + pojo.getEmailBody() + " successfully created");
    }

}
