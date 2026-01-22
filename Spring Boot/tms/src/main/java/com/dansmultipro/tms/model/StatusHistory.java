package com.dansmultipro.tms.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "t_status_history")
public class StatusHistory extends BaseModel {

    @ManyToOne
    @JoinColumn(name = "status_id", nullable = false)
    private TicketStatus status;

    @ManyToOne
    @JoinColumn(name = "ticket_id", nullable = false)
    private Ticket ticket;

    public TicketStatus getStatus() {
        return status;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setStatus(TicketStatus status) {
        this.status = status;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

}
