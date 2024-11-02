package models;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private static Cart instance;
    private List<MenuItem> items;
    private User user;

    private Cart(User user) {
        this.items = new ArrayList<>();
        this.user = user;
    }

    public static Cart getInstance(User user) {
        if (instance == null) {
            instance = new Cart(user);
        }
        return instance;
    }

    public void addItem(MenuItem item) {
        items.add(item);
    }

    public List<MenuItem> getItems() {
        return items;
    }

    public double getTotalPrice() {
        return items.stream().mapToDouble(MenuItem::getPrice).sum();
    }

    public void clearCart() {
        items.clear();
    }
}
