package com.dansmultipro.tms.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "t_m_ticket_status")
public class TicketStatus extends BaseModel {

    @Column(nullable = false, length = 35)
    private String name;

    @Column(nullable = false, length = 5, unique = true)
    private String code;

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
