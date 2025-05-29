package model;

public abstract class MenuItem {
    protected String name;
    protected int price;
    protected String imagePath; // 이미지 추가 위한 변수

    public MenuItem(String name, int price, String imagePath) {
        this.name = name;
        this.price = price;
        this.imagePath = imagePath;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public String getImagePath() {
        return imagePath;
    } // 이미지 경로 getter 함수, MenuRepository에서 menuList.add(new Coffee("아메리카노", 3000, "images/americano.png")); 와 같이 생성 가능

    // 공통적으로 사용할 메소드(카테고리를 반환)
    public abstract String getCategory();
}
