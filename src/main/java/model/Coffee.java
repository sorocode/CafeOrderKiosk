package model;

public class Coffee extends MenuItem {
    private final int shotPrice = 500;
    private int extraShots = 0;

    public Coffee(String name, int price) {
        super(name, price);
    }

    @Override
    public String getCategory() {
        return "Coffee";
    }

    @Override
    public int getPrice() {
        return this.price + (shotPrice * extraShots);
    }
}
