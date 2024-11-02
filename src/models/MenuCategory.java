package models;

import java.util.ArrayList;
import java.util.List;

public class MenuCategory {
    private String name;
    private List<Object> items = new ArrayList<>();

    public MenuCategory(String name) {
        this.name = name;
    }

    public void addItem(Object item) {
        items.add(item);
    }

    public List<Object> getItems() {
        return items;
    }

    public String getName() {
        return name;
    }
}
