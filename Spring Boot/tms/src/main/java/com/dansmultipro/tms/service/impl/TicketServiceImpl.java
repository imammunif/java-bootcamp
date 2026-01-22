package com.dansmultipro.tms.service.impl;

import com.dansmultipro.tms.constant.StatusCode;
import com.dansmultipro.tms.dto.CreateResponseDto;
import com.dansmultipro.tms.dto.UpdateResponseDto;
import com.dansmultipro.tms.dto.ticket.CreateTicketRequestDto;
import com.dansmultipro.tms.dto.ticket.TicketResponseDto;
import com.dansmultipro.tms.dto.ticket.UpdateTicketRequestDto;
import com.dansmultipro.tms.exception.DataMissMatchException;
import com.dansmultipro.tms.exception.NotFoundException;
import com.dansmultipro.tms.model.Product;
import com.dansmultipro.tms.model.Ticket;
import com.dansmultipro.tms.model.TicketStatus;
import com.dansmultipro.tms.model.User;
import com.dansmultipro.tms.repository.ProductRepo;
import com.dansmultipro.tms.repository.TicketRepo;
import com.dansmultipro.tms.repository.TicketStatusRepo;
import com.dansmultipro.tms.repository.UserRepo;
import com.dansmultipro.tms.service.TicketService;
import com.dansmultipro.tms.util.RandomGenerator;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class TicketServiceImpl extends BaseService implements TicketService {

    private final TicketRepo ticketRepo;
    private final TicketStatusRepo ticketStatusRepo;
    private final UserRepo userRepo;
    private final ProductRepo productRepo;


    public TicketServiceImpl(TicketRepo ticketRepo, UserRepo userRepo, TicketStatusRepo ticketStatusRepo, ProductRepo productRepo) {
        this.ticketRepo = ticketRepo;
        this.userRepo = userRepo;
        this.ticketStatusRepo = ticketStatusRepo;
        this.productRepo = productRepo;
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

    @Transactional(rollbackOn = Exception.class)
    @Override
    public CreateResponseDto create(CreateTicketRequestDto data) {
        User customer = userRepo.findById(UUID.fromString(data.getCustomerId())).orElseThrow(
                () -> new NotFoundException("Customer not found")
        );
        Product product = productRepo.findById(UUID.fromString(data.getProductId())).orElseThrow(
                () -> new NotFoundException("Product not found")
        );
        TicketStatus status = ticketStatusRepo.findByCode(StatusCode.OPEN.getCode()).orElseThrow(
                () -> new NotFoundException("Ticket status not found")
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

        return new CreateResponseDto(createdTicket.getId(), "Saved");
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public UpdateResponseDto update(String id, String statusCode, UpdateTicketRequestDto data) {
        Ticket ticket = ticketRepo.findById(UUID.fromString(id)).orElseThrow(
                () -> new NotFoundException("Ticket not found")
        );
        if (!ticket.getVersion().equals(data.getVersion())) {
            throw new DataMissMatchException("Version not match");
        }
        Ticket updateTicket = prepareForUpdate(ticket);
        String currentStatus = ticket.getStatus().getCode();

        //        if (statusCode.equals(StatusCode.OPEN) || statusCode.equals(StatusCode.REOPEN)) {
        //            if (currentStatus.equals(StatusCode.OPEN.getCode()) || currentStatus.equals(StatusCode.REOPEN.getCode())) {
        //                throw new InvalidStatusException("Ticket is already open");
        //            }
        //            updateTicket.setStatus(StatusCode.OPEN.getCode());
        //        }
        return null;
    }

}
