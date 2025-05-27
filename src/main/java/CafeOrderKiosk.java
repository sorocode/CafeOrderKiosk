import controller.MenuController;
import controller.OrderController;
import model.MenuRepository;
import view.KioskMainView;

public class CafeOrderKiosk {
    public static void main(String[] args) {
        MenuRepository repo = new MenuRepository();

        KioskMainView[] viewRef = new KioskMainView[1]; // 뷰를 참조할 수 있도록 래핑

        OrderController orderController = new OrderController(repo, null); // 뷰는 나중에 주입
        MenuController menuController = new MenuController(repo, null);
        
        KioskMainView view = new KioskMainView(menuController, orderController);
        orderController.setView(view);
        menuController.setView(view);

    }
}
