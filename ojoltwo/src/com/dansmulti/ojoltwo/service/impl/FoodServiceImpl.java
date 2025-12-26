package com.dansmulti.ojoltwo.service.impl;

import com.dansmulti.ojoltwo.model.Driver;
import com.dansmulti.ojoltwo.model.Menu;
import com.dansmulti.ojoltwo.model.Restaurant;
import com.dansmulti.ojoltwo.service.FoodService;

import java.util.Arrays;
import java.util.List;

public class FoodServiceImpl implements FoodService {

    @Override
    public Driver findDriver() {
        return new Driver("Budi", "B 9876 YZ");
    }

    @Override
    public double calculateBill(String from, String to, int qty, double price) {

        return ((qty * price) + (from.length() * to.length() + 20000));
    }

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

}
