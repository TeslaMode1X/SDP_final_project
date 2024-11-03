package controllers;

import models.Cart;
import models.MenuItem;
import models.User;

public class OrderController {
    private Cart cart;

    // Конструктор, принимающий объект User и создающий экземпляр корзины
    public OrderController(User user) {
        this.cart = Cart.getInstance(user);
    }

    // Метод для получения корзины
    public Cart getCart() {
        return cart;
    }

    public void addItemToCart(MenuItem item) {
        cart.addItem(item);
    }

    public void removeItemFromCart(MenuItem item) {
        cart.getItems().remove(item);
    }

    public double checkout() {
        double total = cart.getTotalPrice();
        cart.clearCart(); // Очищаем корзину после оформления заказа
        return total;
    }
}
