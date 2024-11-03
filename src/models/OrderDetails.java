package models;

public class OrderDetails {
    private double amount;
    private String paymentMethod;

    public OrderDetails(double amount, String paymentMethod) {
        this.amount = amount;
        this.paymentMethod = paymentMethod;
    }

    @Override
    public String toString() {
        return "Order: $" + amount + " (" + paymentMethod + ")";
    }
}
