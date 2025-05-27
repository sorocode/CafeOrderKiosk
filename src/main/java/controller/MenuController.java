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

    // KioskMainView의 displayMenuButtons를 사용하면 될 듯 합니다. 
    // TODO: 커피 메뉴 화면 보여주기
    public void showCoffeeMenu() {
    }

    // TODO:음료 메뉴 화면 보여주기
    public void showBeverageMenu() {
    }

    // TODO: 디저트 메뉴 화면 보여주기
    public void showDessertMenu() {
    }
}
