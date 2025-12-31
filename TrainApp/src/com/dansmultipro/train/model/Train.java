package com.dansmultipro.train.model;

import java.time.LocalDateTime;
import java.util.List;

public class Train {

    private String name;
    private String from;
    private String to;
    private LocalDateTime dateTime;
    private List<Railcar> railcars;

    public Train(String name, String from, String to, LocalDateTime dateTime, List<Railcar> railcars) {
        this.name = name;
        this.from = from;
        this.to = to;
        this.dateTime = dateTime;
        this.railcars = railcars;
    }

    public String getName() {
        return name;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public List<Railcar> getRailcars() {
        return railcars;
    }

}
