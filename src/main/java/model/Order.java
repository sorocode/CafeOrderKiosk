package model;

import java.util.ArrayList;
import java.util.List;


public class Order {

    private List<MenuItem> items;
    private PaymentType mode;

    public Order() {
        this.items = new ArrayList<>();
    }

    // 메뉴 추가
    public void addItem(MenuItem item) {
        items.add(item);
    }

    // 메뉴 전체 삭제
    public void clearItems() {
        items.clear();
    }

    // 총 가격 계산
    public int getTotalPrice() {
        int total = 0;
        for (MenuItem item : items) {
            total += item.getPrice();
        }
        return total;
    }

    // 결제 방식 가져오기
    public PaymentType getMode() {
        return this.mode;
    }

    // 결제 방식 설정
    public void setMode(PaymentType m) {
        this.mode = m;
    }

    // 주문 목록 반환
    public List<MenuItem> getItems() {
        return new ArrayList<>(items);
    }

    public String getOrderSummary() {
        StringBuilder sb = new StringBuilder();
        for (MenuItem item : items) {
            sb.append(item.getName()).append(" - ").append(item.getPrice()).append("원\n");
        }
        sb.append("총합: ").append(getTotalPrice()).append("원");
        return sb.toString();
    }
}
