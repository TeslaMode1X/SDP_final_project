package models;

import java.util.ArrayList;
import java.util.List;

public class OrderHistoryManager {
    private static OrderHistoryManager instance; // Singleton instance
    private List<OrderDetails> orderHistory; // Stores all orders

    private OrderHistoryManager() {
        orderHistory = new ArrayList<>(); // Initialize the order history list
    }

    public static synchronized OrderHistoryManager getInstance() {
        if (instance == null) {
            instance = new OrderHistoryManager();
        }
        return instance;
    }

    // Method to add an order to history
    public void addOrder(OrderDetails order) {
        orderHistory.add(order);
    }

    // Get full order history
    public List<OrderDetails> getOrderHistory() {
        return orderHistory;
    }

    // Clear order history if needed
    public void clearHistory() {
        orderHistory.clear();
    }
}
