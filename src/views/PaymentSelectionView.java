package views;

import javax.swing.*;
import java.awt.*;
import java.util.List;

import controllers.OrderController;
import models.OrderDetails;

public class PaymentSelectionView extends JFrame {
    private double totalAmount;
    private OrderDetails orderDetails;
    private HistoryView historyView;

    public PaymentSelectionView(double totalAmount, OrderDetails orderDetails, HistoryView historyView) {
        this.totalAmount = totalAmount;
        this.orderDetails = orderDetails;
        this.historyView = historyView;

        setTitle("Select Payment Method");
        setSize(400, 200);
        setLayout(new GridLayout(3, 1, 10, 10));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBackground(new Color(245, 245, 245));

        // Set custom colors
        Color buttonColor = new Color(100, 150, 250);
        Font buttonFont = new Font("Arial", Font.BOLD, 16);

        JButton payOnDeliveryButton = new JButton("Pay on Delivery");
        payOnDeliveryButton.setBackground(buttonColor);
        payOnDeliveryButton.setForeground(Color.WHITE);
        payOnDeliveryButton.setFont(buttonFont);
        payOnDeliveryButton.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
        payOnDeliveryButton.setOpaque(true);
        payOnDeliveryButton.setBorderPainted(false);
        payOnDeliveryButton.setFocusPainted(false);

        JButton payByCardButton = new JButton("Pay by Card");
        payByCardButton.setBackground(buttonColor);
        payByCardButton.setForeground(Color.WHITE);
        payByCardButton.setFont(buttonFont);
        payByCardButton.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
        payByCardButton.setOpaque(true);
        payByCardButton.setBorderPainted(false);
        payByCardButton.setFocusPainted(false);

        payOnDeliveryButton.addActionListener(e -> handlePayment("On Delivery", orderDetails));
        payByCardButton.addActionListener(e -> handlePayment("By Card", orderDetails));

        add(payOnDeliveryButton);
        add(payByCardButton);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void handlePayment(String method, OrderDetails orderDetails) {
        orderDetails.setPaymentMethod(method);

        // Display confirmation message
        JOptionPane.showMessageDialog(this,
                "Order received via " + method + " payment method.",
                "Confirmation",
                JOptionPane.INFORMATION_MESSAGE);

        // If the payment method is "On Delivery", update history directly
        if (method.equals("On Delivery")) {
            historyView.updateHistory(List.of(orderDetails));
        } else if (method.equals("By Card")) {
            // Only open the PaymentView and wait for successful payment to update history
            new PaymentView(totalAmount, orderDetails, historyView);
        }

        dispose(); // Close the PaymentSelectionView
    }
}
