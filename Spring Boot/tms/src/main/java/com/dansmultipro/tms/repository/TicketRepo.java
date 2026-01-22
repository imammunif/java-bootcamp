package com.dansmultipro.tms.repository;

import com.dansmultipro.tms.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TicketRepo extends JpaRepository<Ticket, UUID> {
}
