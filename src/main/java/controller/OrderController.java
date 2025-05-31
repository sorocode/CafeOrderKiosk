package controller;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.swing.*;

import model.MenuItem;
import model.MenuRepository;
import model.Order;
import model.PaymentType;
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

        JDialog dialog = new JDialog(view, "결제 방식 선택", true);
        dialog.setSize(300, 150);
        dialog.setLayout(new BorderLayout());
        dialog.setLocationRelativeTo(view); // view의 중앙에 다이얼로그 위치시키기
        JLabel label = new JLabel("결제 방식을 선택하세요 (남은 시간: 10초)", SwingConstants.CENTER);
        JButton cardButton = new JButton("카드");
        JButton cashButton = new JButton("현금");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(cardButton);
        buttonPanel.add(cashButton);

        dialog.add(label, BorderLayout.NORTH);
        dialog.add(buttonPanel, BorderLayout.CENTER);

        AtomicBoolean selected = new AtomicBoolean(false);
        Timer countdownTimer = new Timer(1000, null); // 1초마다 카운트다운

        final int[] timeLeft = {10};

        countdownTimer.addActionListener(e -> {
            timeLeft[0]--;
            if (timeLeft[0] > 0) {
                label.setText("결제 방식을 선택하세요 (남은 시간: " + timeLeft[0] + "초)");
            } else {
                countdownTimer.stop();
                if (!selected.get()) {
                    dialog.dispose();
                    order.clearItems();
                    SwingUtilities.invokeLater(() ->
                        view.updateOrderSummary("선택 시간이 초과되어 주문이 초기화되었습니다."));
                }
            }
        });
// 버튼 동작 설정
        ActionListener choosePayment = ae -> {
            if (selected.get()) return; // 중복 방지

            selected.set(true);
            countdownTimer.stop();
            dialog.dispose();

            PaymentType method = ae.getSource() == cardButton ? PaymentType.CARD : PaymentType.CASH;
            order.setMode(method); // 카드=0, 현금=1

            int total = order.getTotalPrice();
            String payment = (method == PaymentType.CARD) ? "카드" : "현금";
            view.showMessage("결제 완료! (" + payment + ")\n총액: " + total + "원");

            order.clearItems();
            view.updateOrderSummary("");
        };

        cardButton.addActionListener(choosePayment);
        cashButton.addActionListener(choosePayment);

        countdownTimer.start();
        dialog.setVisible(true);
    }

}
