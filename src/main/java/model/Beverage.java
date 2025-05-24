package model;


public class Beverage extends MenuItem {
    public Beverage(String name, int price) {
        super(name, price);
    }

    @Override
    public String getCategory() {
        return "Beverage";
    }
}
