package com.dansmultipro.tms.repository;

import com.dansmultipro.tms.model.TicketStatusHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TicketStatusHistoryRepo extends JpaRepository<TicketStatusHistory, UUID> {
}
