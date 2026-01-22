package com.dansmultipro.tms.model;

import jakarta.persistence.*;

@Entity
@Table(name = "t_ticket_message")
public class TicketMessage extends BaseModel {

    @ManyToOne
    @JoinColumn(name = "ticket_id", nullable = false)
    private Ticket ticket;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private String description;

    public Ticket getTicket() {
        return ticket;
    }

    public User getUser() {
        return user;
    }

    public String getDescription() {
        return description;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
