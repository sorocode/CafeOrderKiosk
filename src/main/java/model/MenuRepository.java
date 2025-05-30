package model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MenuRepository {
    private List<MenuItem> menuList;

    public MenuRepository() {
        menuList = new ArrayList<>();

        menuList.add(new Coffee("아메리카노", 3000, "/images/americano.png"));
        menuList.add(new Coffee("카페라떼", 3500, "/images/latte.png"));
        menuList.add(new Coffee("카라멜마끼야또", 4000, "/images/caramel.jpg"));
        menuList.add(new Coffee("카페모카", 3800, "/images/mocha.jpg"));
        menuList.add(new Coffee("에스프레소", 2000, "/images/espresso.jpg"));

        menuList.add(new Beverage("레몬에이드", 4000, "/images/lemonade.png"));
        menuList.add(new Beverage("쿠앤크프라푸치노", 3800, "/images/쿠앤크프라푸치노.jpeg"));
        menuList.add(new Beverage("블루베리에이드", 3800, "/images/blueberryade.jpg"));
        menuList.add(new Beverage("청귤에이드", 3800, "/images/청귤.jpg"));
        menuList.add(new Beverage("아이스티", 3800, "/images/icetea.jpg"));

        menuList.add(new Dessert("치즈케이크", 4500, "/images/cheesecake.jpg"));
        menuList.add(new Dessert("초코케이크", 4500, "/images/chocolatecake.jpg"));
        menuList.add(new Dessert("딸기케이크", 4500, "/images/strawberrycake.jpg"));
        menuList.add(new Dessert("마카롱", 2000, "/images/macaron.jpg"));
    }

    public List<MenuItem> getAllMenu() {
        return menuList;
    }

    public MenuItem findByName(String name) {
        for (MenuItem item : menuList) {
            if (item.getName().equals(name)) {
                return item;
            }
        }
        return null;
    }


    public List<MenuItem> getMenuByCategory(String category) {
        return menuList.stream()
            .filter(item -> item.getCategory().equalsIgnoreCase(category))
            .collect(Collectors.toList());
    }
}
