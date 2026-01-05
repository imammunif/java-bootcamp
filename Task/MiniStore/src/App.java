import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class App {
    public static void main(String[] args) throws Exception {

//        ProductService productService = new ProductServiceImpl();
//        HistoryService historyService = new HistoryServiceImpl();
//
//        ProductView productView = new ProductView(productService);
//        CartView cartView = new CartView(productService, historyService);
//        HistoryView historyView = new HistoryView(historyService);
//
//        new MainView(productView, cartView, historyView).show();

        // 1.
        Class<?> productServiceImplClazz = Class.forName("com.dansmultipro.ministore.service.impl.ProductServiceImpl");
        Class<?> productServiceClazz = Class.forName("com.dansmultipro.ministore.service.ProductService");
        Constructor<?> productServiceImplConstructor = productServiceImplClazz.getConstructor();
        Object productServiceObject = productServiceImplConstructor.newInstance();

        Class<?> historyServiceImplClazz = Class.forName("com.dansmultipro.ministore.service.impl.HistoryServiceImpl");
        Class<?> historyServiceClazz = Class.forName("com.dansmultipro.ministore.service.HistoryService");
        Constructor<?> historyServiceImplConstructor = historyServiceImplClazz.getConstructor();
        Object historyServiceObject = historyServiceImplConstructor.newInstance();

        Class<?> productViewClazz = Class.forName("com.dansmultipro.ministore.view.ProductView.java");
        Constructor<?> productViewConstructor = productViewClazz.getConstructor(productServiceClazz);
        Object productView = productViewConstructor.newInstance(productServiceImplClazz);

        Class<?> historyViewClazz = Class.forName("com.dansmultipro.ministore.view.HistoryView.java");
        Constructor<?> historyViewConstructor = historyViewClazz.getConstructor(historyServiceClazz);
        Object historyView = historyViewConstructor.newInstance(historyServiceImplClazz);

        Class<?> cartViewClazz = Class.forName("com.dansmultipro.ministore.view.CartView.java");
        Constructor<?> cartViewConstructor = productViewClazz.getConstructor(productServiceClazz, historyViewClazz);
        Object cartView = cartViewConstructor.newInstance(productServiceImplClazz, historyServiceImplClazz);

        Class<?> mainViewClazz = Class.forName("com.dansmultipro.ministore.view.CartView.java");
        Constructor<?> mainViewConstructor = mainViewClazz.getConstructor(productView, cartView, historyView);



    }
}