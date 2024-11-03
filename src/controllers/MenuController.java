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
        // Drinks
        MenuCategory drinks = new MenuCategory("Drinks");
        drinks.addItem(new MenuItem("Coffee", 2.5));
        drinks.addItem(new MenuItem("Tea", 1.5));
        drinks.addItem(new MenuItem("Soda", 1.0));
        drinks.addItem(new MenuItem("Juice", 1.8));
        drinks.addItem(new MenuItem("Milkshake", 3.0));
        drinks.addItem(new MenuItem("Water", 1.0));
        drinks.addItem(new MenuItem("Iced Coffee", 2.8));
        drinks.addItem(new MenuItem("Lemonade", 2.0));
        drinks.addItem(new MenuItem("Smoothie", 3.5));
        drinks.addItem(new MenuItem("Hot Chocolate", 2.2));

        // Snacks
        MenuCategory snacks = new MenuCategory("Snacks");
        snacks.addItem(new MenuItem("French Fries", 2.0));
        snacks.addItem(new MenuItem("Onion Rings", 2.2));
        snacks.addItem(new MenuItem("Chicken Nuggets", 3.0));
        snacks.addItem(new MenuItem("Mozzarella Sticks", 3.5));
        snacks.addItem(new MenuItem("Chips", 1.5));
        snacks.addItem(new MenuItem("Garlic Bread", 2.5));
        snacks.addItem(new MenuItem("Spring Rolls", 2.8));
        snacks.addItem(new MenuItem("Nachos", 3.0));
        snacks.addItem(new MenuItem("Pretzel", 2.3));
        snacks.addItem(new MenuItem("Popcorn", 1.8));

        // Burgers
        MenuCategory burgers = new MenuCategory("Burgers");
        burgers.addItem(new MenuItem("Classic Burger", 5.0));
        burgers.addItem(new MenuItem("Cheeseburger", 5.5));
        burgers.addItem(new MenuItem("Double Burger", 6.5));
        burgers.addItem(new MenuItem("Vegan Burger", 6.0));
        burgers.addItem(new MenuItem("Chicken Burger", 5.5));
        burgers.addItem(new MenuItem("BBQ Burger", 6.2));
        burgers.addItem(new MenuItem("Bacon Burger", 6.8));
        burgers.addItem(new MenuItem("Mushroom Burger", 6.5));
        burgers.addItem(new MenuItem("Fish Burger", 5.7));
        burgers.addItem(new MenuItem("Spicy Burger", 6.0));

        // Combos
        MenuCategory combos = new MenuCategory("Combos");
        combos.addItem(new MenuItem("Combo 1 (Burger + Fries)", 7.0));
        combos.addItem(new MenuItem("Combo 2 (Cheeseburger + Soda)", 6.5));
        combos.addItem(new MenuItem("Combo 3 (Double Burger + Fries + Soda)", 9.0));
        combos.addItem(new MenuItem("Combo 4 (Chicken Nuggets + Fries + Soda)", 8.0));
        combos.addItem(new MenuItem("Combo 5 (Vegan Burger + Juice)", 7.5));
        combos.addItem(new MenuItem("Combo 6 (Classic Burger + Onion Rings)", 7.8));
        combos.addItem(new MenuItem("Combo 7 (BBQ Burger + Soda)", 7.2));
        combos.addItem(new MenuItem("Combo 8 (Fish Burger + Fries)", 7.3));
        combos.addItem(new MenuItem("Combo 9 (Bacon Burger + Smoothie)", 8.5));
        combos.addItem(new MenuItem("Combo 10 (Spicy Burger + Milkshake)", 8.0));

        // Добавляем категории в основное меню
        mainMenu.addItem(drinks);
        mainMenu.addItem(snacks);
        mainMenu.addItem(burgers);
        mainMenu.addItem(combos);
    }

    public MenuCategory getMainMenu() {
        return mainMenu;
    }
}
