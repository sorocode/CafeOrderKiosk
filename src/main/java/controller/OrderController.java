package controller;

import model.MenuRepository;
import model.Order;
import view.KioskMainView;

public class OrderController {
    private final Order order;
    private final MenuRepository menuRepository;
    private KioskMainView view;

    public OrderController(MenuRepository menuRepository, KioskMainView view) {
        this.menuRepository = menuRepository;
        this.order = new Order();
        this.view = view;
    }

    public void setView(KioskMainView view) {
        this.view = view;
    }

    // TODO: 메뉴 버튼 클릭 시 호출되는 메서드
    public void handleMenuSelection(String menuName) {
    }

    // TODO: 초기화 버튼 클릭 시 호출되는 메서드
    public void handleClearOrder() {
    }

    // TODO: 결제 버튼 클릭 시 호출되는 메서드
    public void handlePayment() {
    }
}
