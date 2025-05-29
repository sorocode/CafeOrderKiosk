package controller;

import model.MenuItem;
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

    /**
     * 메뉴 버튼 클릭 시 호출(옵션 없을 경우)
     */
    public void handleMenuSelection(String menuName) {
        MenuItem item = menuRepository.findByName(menuName);
        if (item != null) {
            order.addItem(item);
            view.updateOrderSummary(order.getOrderSummary());
        } else {
            view.showError("해당 메뉴를 찾을 수 없습니다: " + menuName);
        }
    }

    /**
     * 메뉴 선택(옵션 반영을 위한 메소드 오버로딩)
     */
    public void handleMenuSelection(MenuItem item) {
        order.addItem(item);
        view.updateOrderSummary(order.getOrderSummary());
    }

    /**
     * '초기화' 버튼 클릭 시 호출
     */
    public void handleClearOrder() {
        order.clearItems();
        view.updateOrderSummary("주문이 초기화되었습니다.");
    }

    /**
     * '결제' 버튼 클릭 시 호출
     */
    public void handlePayment() {
        if (order.getItems().isEmpty()) {
            view.showError("주문 내역이 없습니다.");
            return;
        }

        int total = order.getTotalPrice();
        view.showMessage("결제 완료! 총액: " + total + "원");

        order.clearItems();
        view.updateOrderSummary(""); // 주문창 비우기
    }

}
