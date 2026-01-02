package com.dansmultipro.ministore.view;

import com.dansmultipro.ministore.listener.OnBackListener;
import com.dansmultipro.ministore.util.ScannerUtil;

public class MainView {

    private final ProductView productView;
    private final CartView cartView;
    private final HistoryView historyView;
//    private List<Order> orderList = new ArrayList<>();
//    private History history = new History(orderList);

    public MainView(ProductView productView, CartView cartView, HistoryView historyView) {
        this.productView = productView;
        this.cartView = cartView;
        this.historyView = historyView;
    }

    public void show() {
        System.out.println("""
                ======= Mini Store =======
                [1] Show available products
                [2] Show my cart
                [3] Show order history
                [4] Exit
                """);

        int chosen = ScannerUtil.scanLimitedOption("\nSelect [1-4] : ", 4);
        if (chosen == 1) {
            productView.show(() -> show());
        } else if (chosen == 2) {
            cartView.show(() -> show());
        } else if (chosen == 3) {
            historyView.show(() -> show());
        } else if (chosen == 4) {
            return;
        }
        show();
    }
}
