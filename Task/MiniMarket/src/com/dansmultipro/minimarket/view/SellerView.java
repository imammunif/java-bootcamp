package com.dansmultipro.minimarket.view;

import com.dansmultipro.minimarket.listener.OnBackListener;
import com.dansmultipro.minimarket.service.SellerService;
import com.dansmultipro.minimarket.service.MarketService;

public class SellerView {

    private final MarketService marketService;
    private final SellerService sellerService;

    public SellerView(MarketService marketService, SellerService sellerService) {
        this.marketService = marketService;
        this.sellerService = sellerService;
    }

    public void show(OnBackListener listener) {
        System.out.println("Seller View");
        listener.onBackPressed();
    }
}
