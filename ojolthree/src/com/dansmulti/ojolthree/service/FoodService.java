package com.dansmulti.ojolthree.service;

import com.dansmulti.ojolthree.model.*;

import java.util.List;

public interface FoodService {

    List<Restaurant> getRestaurants();

    void setCartItems(Cart cart, CartItem cartItem, Menu menu, int qty);

    void setItemQty(CartItem cartItem, int additionQty);

    void setItemSubtotal(CartItem cartItem);

    Driver findDriver();

    Double getCartGrandtotal(Cart cart);

    void setCartGrandtotal(Cart cart);

    double calculateBill(Cart cart, String from, String to);
}
