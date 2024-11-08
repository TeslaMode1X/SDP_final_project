package controllers;

import models.Cart;
import models.MenuItem;
import models.OrderDetails;
import models.OrderHistoryManager;
import models.User;

import java.time.LocalDate;

public class OrderController {
    private Cart cart;

    // Constructor that takes a User and gets the user's Cart instance
    public OrderController(User user) {
        this.cart = Cart.getInstance(user);
    }

    // Method to get the cart
    public Cart getCart() {
        return cart;
    }

    // Method to add item to cart
    public void addItemToCart(MenuItem item) {
        cart.addItem(item);
    }

    // Method to remove item from cart
    public void removeItemFromCart(MenuItem item) {
        cart.getItems().remove(item);
    }

    // Checkout method that adds the order to global history and returns total price
    public double checkout() {
        double total = cart.getTotalPrice();
        String orderDate = LocalDate.now().toString();  // Current date as a string

        // Create OrderDetails with total price, order date, and cart items
        OrderDetails order = new OrderDetails(total, orderDate, cart.getItems());

        // Add order to the global OrderHistoryManager for persistence
        OrderHistoryManager.getInstance().addOrder(order);

        // Clear the cart after checkout
        cart.clearCart();

        return total;
    }
}
