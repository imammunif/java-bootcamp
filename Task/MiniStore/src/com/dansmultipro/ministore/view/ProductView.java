package com.dansmultipro.ministore.view;

import com.dansmultipro.ministore.listener.OnBackListener;
import com.dansmultipro.ministore.service.HistoryService;
import com.dansmultipro.ministore.service.ProductService;
import com.dansmultipro.ministore.util.ScannerUtil;

public class ProductView {

    private final ProductService productService;
    private final HistoryService historyService;

    public ProductView(ProductService productService, HistoryService historyService) {
        this.productService = productService;
        this.historyService = historyService;
    }

    public void show(OnBackListener listener) {
//        List<Product> products =
        System.out.println("""
                ---- Available Products ----
                """);
//        for (int i = 0; i < trains.size(); i++) {
//            System.out.println((i + 1) + ". " + trains.get(i).getName() + " (" + trains.get(i).getFrom() + " - " + trains.get(i).getTo() + ") " + trains.get(i).getDateTime().format(timeFormat));
//        }
        int options = ScannerUtil.scanLimitedOption("Select : ", 2);

        listener.onBackPressed();
    }
}
