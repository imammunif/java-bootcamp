package com.dansmulti.ojolthree.service.impl;

import com.dansmulti.ojolthree.model.*;
import com.dansmulti.ojolthree.service.FoodService;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class FoodServiceImpl implements FoodService {

    @Override
    public List<Restaurant> getRestaurants() {
        return Arrays.asList(
                new Restaurant("Duta Minang", "Jl. Merdeka, DKI", Arrays.asList(
                        new Menu("Air mineral", 3000),
                        new Menu("Es teh", 5000),
                        new Menu("Es jeruk", 5000),
                        new Menu("Nasi ayam bakar", 25000),
                        new Menu("Nasi ayam goreng", 25000),
                        new Menu("Nasi rendang", 30000)
                )),
                new Restaurant("Gacoan", "Jl. Merpati, Depok", Arrays.asList(
                        new Menu("Mi setan lv 1", 18000),
                        new Menu("Mi setan lv 2", 19000),
                        new Menu("Mi setan lv 3", 20000),
                        new Menu("Mi iblis lv 1", 18000),
                        new Menu("Mi iblis lv 2", 19000),
                        new Menu("Mi iblis lv 3", 20000)
                )),
                new Restaurant("Nasi Kulit Syurga", "Jl. Mawar, Bekasi", Arrays.asList(
                        new Menu("Nasi ayam kulit", 16000),
                        new Menu("Nasi ayam kulit bumbu", 18000),
                        new Menu("Nasi paru kulit", 16000),
                        new Menu("Nasi paru kulit bumbu", 18000),
                        new Menu("Nasi ayam paru kulit", 21000),
                        new Menu("Nasi telur kulit", 16000)
                )),
                new Restaurant("Warteg Kharisma", "Jl. Anggrek, Tangerang", Arrays.asList(
                        new Menu("Nasi ayam", 18000),
                        new Menu("Nasi telur goreng", 14000),
                        new Menu("Nasi telur bacam", 14000),
                        new Menu("Nasi nila", 17000),
                        new Menu("Nasi gurameh", 17000),
                        new Menu("Nasi lele", 17000)
                )),
                new Restaurant("Warkop Sahabat", "Jl. Tulip, Depok", Arrays.asList(
                        new Menu("Nasi telur", 18000),
                        new Menu("Nasi goreng", 14000),
                        new Menu("Indomie", 14000),
                        new Menu("Indomie kuah ", 14000),
                        new Menu("Indomie telur", 14000),
                        new Menu("Indomie kuah telur", 14000)
                ))
        );
    }

    @Override
    public void setCartRestaurant(Cart cart, Restaurant restaurant) {
        if (cart.getRestaurant() == null) {
            cart.setRestaurant(restaurant);
        }
    }

    @Override
    public void setCartItems(Cart cart, CartItem newItem, Menu menu, int qty) {
        List<CartItem> cartItems = cart.getItems();

        for (CartItem item : cartItems) {
            if (item.getMenu().equals(menu)) {
                setItemQty(item, qty);
                return;
            }
        }
        cartItems.add(newItem);
        cart.setItems(cartItems);
    }

    @Override
    public void setItemQty(CartItem cartItem, int additionQty) {
        int newQuantity = cartItem.getQuantity() + additionQty;
        cartItem.setQuantity(newQuantity);
        setItemSubtotal(cartItem);
    }

    @Override
    public void setItemSubtotal(CartItem cartItem) {
        double subtotal = cartItem.getQuantity() * cartItem.getMenu().getPrice();
        cartItem.setSubtotal(subtotal);
    }

    @Override
    public Driver findDriver() {
        List<Driver> drivers = Arrays.asList(
                new Driver("Dono", "B 8485 KX"),
                new Driver("Kasino", "B 9876 YZ"),
                new Driver("Indro", "A 7632 PK"),
                new Driver("Nanu", "D 8542 KT"),
                new Driver("Rudy", "D 3764 HK")
        );
        Random random = new Random();
        return drivers.get(random.nextInt(drivers.size()));
    }

    @Override
    public Double getCartGrandtotal(Cart cart) {
        return cart.getGrandTotal();
    }

    @Override
    public void setCartGrandtotal(Cart cart) {
        List<CartItem> cartItems = cart.getItems();
        double grandTotal = 0;
        for (CartItem item : cartItems) {
            grandTotal += item.getSubtotal();
        }
        cart.setGrandTotal(grandTotal);
    }

    @Override
    public double calculateBill(Cart cart, String from, String to) {
        setCartGrandtotal(cart);
        return (getCartGrandtotal(cart) + (from.length() * to.length() * 200));
    }

}
