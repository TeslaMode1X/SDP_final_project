package models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class OrderDetails {
    private double totalAmount;
    private String paymentMethod;
    private LocalDateTime orderDate;
    private List<MenuItem> items;

    public OrderDetails(double totalAmount, String paymentMethod, List<MenuItem> items) {
        this.totalAmount = totalAmount;
        this.paymentMethod = paymentMethod;
        this.orderDate = LocalDateTime.now();
        this.items = items;
    }

    @Override
    public String toString() {
        StringBuilder itemDetails = new StringBuilder();
        for (MenuItem item : items) {
            itemDetails.append("- ").append(item.getName()).append(" ($").append(item.getPrice()).append(")\n");
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return "Date: " + orderDate.format(formatter) + "\n" +
                "Items:\n" + itemDetails.toString() +
                "Total: $" + totalAmount + "\n" +
                "Payment: " + paymentMethod + "\n";
    }
    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

}
