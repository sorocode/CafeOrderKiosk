package view;

import controller.MenuController;
import controller.OrderController;
import model.Coffee;
import model.MenuItem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class KioskMainView extends JFrame {
    private final MenuController menuController;
    private final OrderController orderController;
    JButton coffeeButton, beverageButton, dessertButton;
    JButton menuButton;
    private JPanel menuPanel;
    private OrderPanel orderPanel;
    private CardLayout cardLayout = new CardLayout();
    private JPanel mainPanel, startPanel, kioskPanel;
    private JButton startButton;

    public KioskMainView(MenuController menuController, OrderController orderController) {
        this.menuController = menuController;
        this.orderController = orderController;

        setTitle("KNU CAFE");
        setSize(800, 1000);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        //==시작 화면==//
        mainPanel = new JPanel();
        mainPanel.setLayout(cardLayout);
        startPanel = new JPanel();
        startPanel.setLayout(new BorderLayout());
        startButton = new JButton("주문하기");
        startButton.addActionListener(new DisplayMenuActionListener());
        startButton.setPreferredSize(new Dimension(600, 200));
        startButton.setFont(new Font("Dialog", Font.PLAIN, 40));

        ImageIcon startIcon = new ImageIcon(getClass().getResource("/images/startimage.png")); // 시작 화면 이미지 생성
        Image image = startIcon.getImage();
        Image updatedstartImage = image.getScaledInstance(600, 700, Image.SCALE_SMOOTH);
        ImageIcon updatedStartIcon = new ImageIcon(updatedstartImage); // 사진 크기 조정 과정

        JLabel startImage = new JLabel(updatedStartIcon);

        startPanel.add(startImage, BorderLayout.CENTER);
        startPanel.add(startButton, BorderLayout.SOUTH);

        kioskPanel = new JPanel();
        kioskPanel.setLayout(new BorderLayout());

        //==상단(카테고리 패널)==//
        JPanel categoryPanel = new JPanel();
        categoryPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 100, 30));
        categoryPanel.setBackground(Color.gray);

        coffeeButton = new JButton("커피");
        beverageButton = new JButton("음료");
        dessertButton = new JButton("디저트");

        coffeeButton.setBackground(Color.lightGray);
        beverageButton.setBackground(Color.lightGray);
        dessertButton.setBackground(Color.lightGray);
        coffeeButton.setPreferredSize(new Dimension(100, 30));
        beverageButton.setPreferredSize(new Dimension(100, 30));
        dessertButton.setPreferredSize(new Dimension(100, 30)); // 버튼 색깔 및 크기 조정

        coffeeButton.addActionListener(new DisplayMenuActionListener());
        beverageButton.addActionListener(new DisplayMenuActionListener());
        dessertButton.addActionListener(new DisplayMenuActionListener());

        categoryPanel.add(coffeeButton);
        categoryPanel.add(beverageButton);
        categoryPanel.add(dessertButton);
        //==중앙(메뉴)==//
        menuPanel = new JPanel();
        menuPanel.setLayout(new GridLayout(0, 2));  // 2열 레이아웃


        //==우측(결제사이드바)==//
        orderPanel = new OrderPanel();
        orderPanel.setClearAction(() -> orderController.handleClearOrder());
        orderPanel.setPayAction(() -> orderController.handlePayment());
        kioskPanel.add(categoryPanel, BorderLayout.NORTH);
        kioskPanel.add(new JScrollPane(menuPanel), BorderLayout.CENTER); // 메뉴는 스크롤이 가능하도록 설정함

        kioskPanel.add(orderPanel, BorderLayout.EAST);

        mainPanel.add(startPanel, "start"); // mainPanel에 start라는 이름으로 시작화면 추가
        mainPanel.add(kioskPanel, "kiosk"); // mainPanel에 kiosk라는 이름으로 주문화면 추가

        add(mainPanel);

        setVisible(true);

    }

    /**
     * 메뉴 버튼 동적 생성
     */
    public void displayMenuButtons(List<MenuItem> items) {
        menuPanel.removeAll();
        for (MenuItem item : items) {
            ImageIcon icon = new ImageIcon(getClass().getResource(item.getImagePath()));
            Image image = icon.getImage(); // 아이콘에서 이미지 추출
            Image updatedImage = image.getScaledInstance(180, 150, Image.SCALE_SMOOTH); // 이미지 크기 조절
            ImageIcon updatedIcon = new ImageIcon(updatedImage);

            menuButton = new JButton("<html><body><center>" + item.getName() + " <br> " + item.getPrice() + "원</center></body></html>", updatedIcon);
            // 버튼에서 줄바꿈 시 html 이용
            menuButton.setPreferredSize(new Dimension(200, 250));
            menuButton.setVerticalTextPosition(SwingConstants.BOTTOM);
            menuButton.setHorizontalTextPosition(SwingConstants.CENTER); // 텍스트 가운데 아래쪽 위치
            menuButton.setBackground(Color.white);
            menuButton.addActionListener(e -> {
                if (item instanceof Coffee) {
                    String input = JOptionPane.showInputDialog(this, "샷 추가 수를 입력하세요 (예: 0)", "샷 추가", JOptionPane.PLAIN_MESSAGE);
                    if (input != null) {
                        try {
                            int shots = Integer.parseInt(input.trim());
                            if (shots < 0) throw new NumberFormatException();
                            Coffee selected = new Coffee(item.getName(), item.getPrice(), item.getImagePath());
                            selected.setExtraShots(shots);
                            orderController.handleMenuSelection(selected);
                        } catch (NumberFormatException ex) {
                            showError("유효한 숫자를 입력하세요.");
                        }
                    }
                } else {
                    orderController.handleMenuSelection(item.getName());
                }
            });
            menuPanel.add(menuButton);
        }
        menuPanel.revalidate();     // 카테고리 변경으로 인한 메뉴패널 변경 반영 위해 추가해야 함(위치, 구성 변경을 반영함)
        menuPanel.repaint();
    }

    //==주문 관련 로직 작성==/

    /**
     * 주문 요약 갱신
     */
    public void updateOrderSummary(String summary) {
        orderPanel.updateOrderSummary(summary);
    }

    /**
     * 주문 에러
     */
    public void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "오류", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * 메시지
     */
    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "안내", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * 카테고리 버튼 클릭 시 메뉴 패널 변경
     */
    public class DisplayMenuActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == startButton) { // 시작 버튼 누를 때 커피 화면 띄우기
                cardLayout.show(mainPanel, "kiosk");
                coffeeButton.setBackground(Color.black);
                coffeeButton.setForeground(Color.white);
                beverageButton.setBackground(Color.lightGray);
                beverageButton.setForeground(Color.black);
                dessertButton.setBackground(Color.lightGray);
                dessertButton.setForeground(Color.black);
                menuController.showCoffeeMenu();

            } else if (e.getSource() == coffeeButton) {
                coffeeButton.setBackground(Color.black);
                coffeeButton.setForeground(Color.white);
                beverageButton.setBackground(Color.lightGray);
                beverageButton.setForeground(Color.black);
                dessertButton.setBackground(Color.lightGray);
                dessertButton.setForeground(Color.black);
                menuController.showCoffeeMenu();
            } else if (e.getSource() == beverageButton) {
                coffeeButton.setBackground(Color.lightGray);
                coffeeButton.setForeground(Color.black);
                beverageButton.setBackground(Color.black);
                beverageButton.setForeground(Color.white);
                dessertButton.setBackground(Color.lightGray);
                dessertButton.setForeground(Color.black);
                menuController.showBeverageMenu();
            } else if (e.getSource() == dessertButton) {
                coffeeButton.setBackground(Color.lightGray);
                coffeeButton.setForeground(Color.black);
                beverageButton.setBackground(Color.lightGray);
                beverageButton.setForeground(Color.black);
                dessertButton.setBackground(Color.black);
                dessertButton.setForeground(Color.white);
                menuController.showDessertMenu();
            }
        }

    }
}
