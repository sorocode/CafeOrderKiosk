package model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MenuRepository {
    private List<MenuItem> menuList;

    public MenuRepository() {
        menuList = new ArrayList<>();

        menuList.add(new Coffee("아메리카노", 3000));
        menuList.add(new Coffee("카페라떼", 3500));

        menuList.add(new Beverage("레몬에이드", 4000));
        menuList.add(new Beverage("아이스티", 3800));

        menuList.add(new Dessert("치즈케이크", 4500));
        menuList.add(new Dessert("마카롱", 2000));
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
