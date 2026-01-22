package com.dansmultipro.tms.repository;

import com.dansmultipro.tms.model.TicketMessage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TicketMessageRepo extends JpaRepository<TicketMessage, UUID> {
}
