package com.dansmultipro.tms.repository;

import com.dansmultipro.tms.model.TicketStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TicketStatusRepo extends JpaRepository<TicketStatus, UUID> {
}
