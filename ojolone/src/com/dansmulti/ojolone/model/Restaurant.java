package com.dansmulti.ojolone.model;

public class Restaurant {
    private String name;
    private String address;
    private Menu[] menu;

    public Restaurant(String name, String address, Menu[] menu) {
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

    public Menu[] getMenus() {
        return menu;
    }
}