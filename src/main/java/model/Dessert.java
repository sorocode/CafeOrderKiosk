package model;

public class Dessert extends MenuItem {
    public Dessert(String name, int price) {
        super(name, price);
    }

    @Override
    public String getCategory() {
        return "Dessert";
    }
}
