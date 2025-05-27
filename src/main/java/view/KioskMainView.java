package view;

import controller.MenuController;
import controller.OrderController;
import model.MenuItem;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class KioskMainView extends JFrame {
    private final MenuController menuController;
    private final OrderController orderController;
    private JPanel menuPanel;
    private OrderPanel orderPanel;

    public KioskMainView(MenuController menuController, OrderController orderController) {
        this.menuController = menuController;
        this.orderController = orderController;

        setTitle("KNU CAFE");
        setSize(1920, 1080);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // TODO: (상단) 카테고리 패널
        JPanel categoryPanel = new JPanel();


        // TODO: (중앙) 메뉴들을 보여주는 패널(버튼으로 구현하시면 될 것 같습니다)
        menuPanel = new JPanel();

        // TODO: (우측): 주문 내역 패널(이 부분은 제가 합니다.)
        orderPanel = new OrderPanel();

        add(categoryPanel, BorderLayout.NORTH);
        add(new JScrollPane(menuPanel), BorderLayout.CENTER); // 메뉴는 스크롤이 가능하도록 설정함
        add(orderPanel, BorderLayout.EAST);

        setVisible(true);
    }

    // TODO: 메뉴 버튼 동적 생성
    public void displayMenuButtons(List<MenuItem> items) {
    }

    // TODO: 주문 관련 로직 작성(이 부분은 제가 합니다)
}
