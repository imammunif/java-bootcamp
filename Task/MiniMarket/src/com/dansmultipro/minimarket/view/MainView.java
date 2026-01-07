package com.dansmultipro.minimarket.view;

import com.dansmultipro.minimarket.constant.UserType;
import com.dansmultipro.minimarket.util.ScannerUtil;

public class MainView {

    private final BuyerView buyerView;
    private final SellerView sellerView;

    public MainView(BuyerView buyerView, SellerView sellerView) {
        this.buyerView = buyerView;
        this.sellerView = sellerView;
    }

    public void show() {
        System.out.println("---- Please login first ----");
        String username = inputUsername();
        String password = inputPassword();
        if (username.equals("seller") && password.equals("seller")) {
            sellerView.show(() -> show());
        } else {
            buyerView.show(() -> show());
        }
        show();
    }

    public String inputUsername() {
        String username = ScannerUtil.scanText("Username: ");
        boolean isExist = false;
        for (UserType user : UserType.values()) {
            if (user.getUsername().equals(username)) {
                isExist = true;
                break;
            }
        }
        if (!isExist) {
            System.out.println("Username not exist");
            inputUsername();
        }
        return username;
    }

    public String inputPassword() {
        String password = ScannerUtil.scanText("Password: ");
        boolean isCorrect = false;
        for (UserType user : UserType.values()) {
            if (user.getPassword().equals(password)) {
                isCorrect = true;
                break;
            }
        }
        if (!isCorrect) {
            System.out.println("Wrong password");
            inputPassword();
        }
        return password;
    }

}

