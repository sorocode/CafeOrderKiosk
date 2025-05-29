package controller;

import model.*;
import view.KioskMainView;

public class MenuController {
    private final MenuRepository menuRepository;
    private KioskMainView view;

    public MenuController(MenuRepository menuRepository, KioskMainView view) {
        this.menuRepository = menuRepository;
        this.view = view;
    }

    public void setView(KioskMainView view) {
        this.view = view;
    }

    //==카테고리 선택==//

    /**
     * 커피
     */
    public void showCoffeeMenu() {
        view.displayMenuButtons(menuRepository.getMenuByCategory("Coffee"));
    }

    /**
     * 음료
     */
    public void showBeverageMenu() {
        view.displayMenuButtons(menuRepository.getMenuByCategory("Beverage"));
    }

    /**
     * 디저트
     */
    public void showDessertMenu() {
        view.displayMenuButtons(menuRepository.getMenuByCategory("Dessert"));
    }
}
