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
        setSize(300, 150);
        setLayout(new GridLayout(2, 1, 10, 10));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JButton payOnDeliveryButton = new JButton("Pay on Delivery");
        JButton payByCardButton = new JButton("Pay by Card");

        payOnDeliveryButton.addActionListener(e -> handlePayment("On Delivery", orderDetails));
        payByCardButton.addActionListener(e -> handlePayment("By Card", orderDetails));

        System.out.println(orderDetails);

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

        // Now print the updated order details
        System.out.println(orderDetails);

        // Update the history view with the new order details
        historyView.updateHistory(List.of(orderDetails));

        dispose();

        if (method.equals("By Card")) {
            new PaymentView(totalAmount, orderDetails, historyView);
        }
    }
}
