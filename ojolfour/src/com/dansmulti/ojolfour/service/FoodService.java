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

    Double getDiscount(String voucher);

    Double getCartGrandtotal(Cart cart);

    void setCartGrandtotal(Cart cart);

    Double calculateBill(Cart cart, String voucher, String from, String to);
}
