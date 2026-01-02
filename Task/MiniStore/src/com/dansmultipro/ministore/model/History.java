package com.dansmultipro.ministore.model;

import java.util.List;

public class History {

    private List<Order> history;

    public History(List<Order> history) {
        this.history = history;
    }

    public List<Order> getHistory() {
        return history;
    }

    public void setHistory(List<Order> history) {
        this.history = history;
    }

}