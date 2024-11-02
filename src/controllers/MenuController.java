package controllers;

import models.MenuCategory;
import models.MenuItem;

public class MenuController {
    private MenuCategory mainMenu;

    public MenuController() {
        mainMenu = new MenuCategory("Main Menu");
        setupMenu();
    }

    private void setupMenu() {
        MenuCategory drinks = new MenuCategory("Drinks");
        drinks.addItem(new MenuItem("Coffee", 2.5));
        drinks.addItem(new MenuItem("Tea", 1.5));

        MenuCategory food = new MenuCategory("Food");
        food.addItem(new MenuItem("Burger", 5.0));
        food.addItem(new MenuItem("Salad", 4.0));

        mainMenu.addItem(drinks);
        mainMenu.addItem(food);
    }

    public MenuCategory getMainMenu() {
        return mainMenu;
    }
}
