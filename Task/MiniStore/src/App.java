import com.dansmultipro.ministore.service.HistoryService;
import com.dansmultipro.ministore.service.ProductService;
import com.dansmultipro.ministore.service.impl.HistoryServiceImpl;
import com.dansmultipro.ministore.service.impl.ProductServiceImpl;
import com.dansmultipro.ministore.view.CartView;
import com.dansmultipro.ministore.view.HistoryView;
import com.dansmultipro.ministore.view.MainView;
import com.dansmultipro.ministore.view.ProductView;

public class App {
    public static void main(String[] args) {

        ProductService productService = new ProductServiceImpl();
        HistoryService historyService = new HistoryServiceImpl();

        ProductView productView = new ProductView(productService, historyService);
        CartView cartView = new CartView(productService, historyService);
        HistoryView historyView = new HistoryView(historyService);

        new MainView(productView, cartView, historyView).show();
    }
}