package model;

public class Dessert extends MenuItem {
    public Dessert(String name, int price, String imagePath) {
        super(name, price, imagePath);
    }

    @Override
    public String getCategory() {
        return "Dessert";
    }
}
