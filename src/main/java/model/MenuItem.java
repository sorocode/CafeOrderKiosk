package model;

public abstract class MenuItem {
    protected String name;
    protected int price;

    public MenuItem(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    // 공통적으로 사용할 메소드(카테고리를 반환)
    public abstract String getCategory();
}
