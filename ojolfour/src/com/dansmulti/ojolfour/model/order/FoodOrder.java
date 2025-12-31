package com.dansmulti.ojolfour.model.order;

import com.dansmulti.ojolfour.model.Cart;
import com.dansmulti.ojolfour.model.constant.OrderType;

import java.time.LocalDateTime;

public class FoodOrder extends Order {

    private Cart cart;

    public FoodOrder(OrderType type, LocalDateTime dateTime, String from, String to) {
        super(type, dateTime, from, to);
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

}
