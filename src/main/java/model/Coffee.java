package model;

public class Coffee extends MenuItem {
    public Coffee(String name, int price) {
        super(name, price);
    }

    @Override
    public String getCategory() {
        return "Coffee";
    }
}
