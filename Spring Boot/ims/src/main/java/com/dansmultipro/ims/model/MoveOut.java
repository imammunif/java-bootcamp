package com.dansmultipro.ims.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "t_move_out")
public class MoveOut extends BaseModel {

    @Column(nullable = false, length = 20, unique = true)
    private String code;

    @Column(nullable = false)
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "agent_id", nullable = false)
    private Agent agent;

    public String getCode() {
        return code;
    }

    public LocalDate getDate() {
        return date;
    }

    public Agent getAgent() {
        return agent;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setAgent(Agent agent) {
        this.agent = agent;
    }

}
