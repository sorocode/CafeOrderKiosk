package model;

public class Coffee extends MenuItem {
    private static final int SHOT_PRICE = 500;
    private int extraShots = 0;

    public Coffee(String name, int price, String imagePath) {
        super(name, price, imagePath);
    }

    public int getExtraShots() {
        return extraShots;
    }

    public void setExtraShots(int count) {
        this.extraShots = count;
    }

    @Override
    public int getPrice() {
        return super.getPrice() + (extraShots * SHOT_PRICE);
    }

    @Override
    public String getName() {
        return super.getName() + (extraShots > 0 ? " (+샷 " + extraShots + ")" : "");
    }

    @Override
    public String getCategory() {
        return "Coffee";
    }
}
