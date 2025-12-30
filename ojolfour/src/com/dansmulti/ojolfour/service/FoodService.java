package com.dansmulti.ojolfour.service;

import com.dansmulti.ojolfour.model.*;

import java.util.List;

public interface FoodService {

    List<Restaurant> getRestaurants();

    void setCartRestaurant(Cart cart, Restaurant restaurant);

    void setCartItems(Cart cart, CartItem cartItem, Menu menu, int qty);

    void setItemQty(CartItem cartItem, int additionQty);

    void setItemSubtotal(CartItem cartItem);

    Driver findDriver();

    Double getCartGrandtotal(Cart cart);

    void setCartGrandtotal(Cart cart);

    double calculateBill(Cart cart, String from, String to);
}
