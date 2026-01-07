package com.dansmultipro.minimarket;

import com.dansmultipro.minimarket.service.BuyerService;
import com.dansmultipro.minimarket.service.MarketService;
import com.dansmultipro.minimarket.service.impl.BuyerServiceImpl;
import com.dansmultipro.minimarket.service.impl.MarketServiceImpl;
import com.dansmultipro.minimarket.view.BuyerView;
import com.dansmultipro.minimarket.view.MainView;
import com.dansmultipro.minimarket.view.SellerView;

public class App {
    public static void main(String[] args) {

        MarketService marketService = new MarketServiceImpl();
        BuyerService buyerService = new BuyerServiceImpl();

        BuyerView buyerView = new BuyerView(marketService, buyerService);
        SellerView sellerView = new SellerView(marketService);

        new MainView(buyerView, sellerView).show();
    }

}
