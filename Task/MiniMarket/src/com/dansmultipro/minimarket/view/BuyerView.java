package com.dansmultipro.minimarket.view;

import com.dansmultipro.minimarket.listener.OnBackListener;
import com.dansmultipro.minimarket.service.BuyerService;
import com.dansmultipro.minimarket.service.MarketService;

public class BuyerView {

    private final MarketService marketService;
    private final BuyerService buyerService;

    public BuyerView(MarketService marketService, BuyerService buyerService) {
        this.marketService = marketService;
        this.buyerService = buyerService;
    }

    public void show(OnBackListener listener) {
        System.out.println("Buyer view");
        listener.onBackPressed();
    }

}
