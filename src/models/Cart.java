package models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cart {
    private static Map<User, Cart> instances = new HashMap<>();  // Store one Cart per User
    private List<MenuItem> items;
    private User user;

    private Cart(User user) {
        this.items = new ArrayList<>();
        this.user = user;
    }

    // Returns a Cart instance specific to the user
    public static Cart getInstance(User user) {
        return instances.computeIfAbsent(user, u -> new Cart(u));
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
