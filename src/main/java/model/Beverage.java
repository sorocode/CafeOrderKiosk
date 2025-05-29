package model;


public class Beverage extends MenuItem {
    public Beverage(String name, int price, String imagePath) {
        super(name, price, imagePath);
    }

    @Override
    public String getCategory() {
        return "Beverage";
    }
}
