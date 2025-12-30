package com.dansmulti.ojolfour.model;

import java.util.List;

public class Restaurant {
    private String name;
    private String address;
    private List<Menu> menu;

    public Restaurant(String name, String address, List<Menu> menu) {
        this.name = name;
        this.address = address;
        this.menu = menu;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public List<Menu> getMenus() {
        return menu;
    }
}