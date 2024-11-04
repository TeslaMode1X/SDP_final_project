package views;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import models.OrderDetails;

public class PaymentView extends JFrame {
    private JLabel totalAmountLabel;
    private JTextField cardNumberField;
    private JTextField expiryDateField;
    private JTextField cvvField;
    private JButton payButton;
    private OrderDetails orderDetails;
    private double totalAmount;

    public PaymentView(double totalAmount, OrderDetails orderDetails, HistoryView historyView) {
        this.orderDetails = orderDetails;
        this.totalAmount = totalAmount;

        setTitle("Payment Window");
        setSize(400, 300);
        setLayout(new GridLayout(6, 2, 10, 10));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setBackground(new Color(245, 245, 245));

        totalAmountLabel = new JLabel("Total Amount: $" + totalAmount);
        cardNumberField = new JTextField(16);
        expiryDateField = new JTextField(5);
        cvvField = new JTextField(3);
        payButton = new JButton("Pay Now");

        // Set custom colors and fonts
        payButton.setBackground(new Color(100, 150, 250));
        payButton.setForeground(Color.WHITE);
        payButton.setFont(new Font("Arial", Font.BOLD, 16));
        payButton.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
        payButton.setOpaque(true);
        payButton.setBorderPainted(false);
        payButton.setFocusPainted(false);

        add(new JLabel("Total:"));
        add(totalAmountLabel);
        add(new JLabel("Card Number:"));
        add(cardNumberField);
        add(new JLabel("Expiry Date (MM/YY):"));
        add(expiryDateField);
        add(new JLabel("CVV:"));
        add(cvvField);
        add(new JLabel()); // Empty label for spacing
        add(payButton);

        payButton.addActionListener(e -> {
            if (cardNumberField.getText().isEmpty() || expiryDateField.getText().isEmpty() || cvvField.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please fill in all payment details.", "Payment Error", JOptionPane.ERROR_MESSAGE);
            } else if (!isValidCardNumber(cardNumberField.getText())) {
                JOptionPane.showMessageDialog(null, "Invalid card number.", "Payment Error", JOptionPane.ERROR_MESSAGE);
            } else if (!isValidExpiryDate(expiryDateField.getText())) {
                JOptionPane.showMessageDialog(null, "Invalid expiry date. Format should be MM/YY.", "Payment Error", JOptionPane.ERROR_MESSAGE);
            } else if (!isValidCVV(cvvField.getText())) {
                JOptionPane.showMessageDialog(null, "Invalid CVV.", "Payment Error", JOptionPane.ERROR_MESSAGE);
            } else {
                // Add the order to history and show success message
                historyView.updateHistory(List.of(orderDetails));
                JOptionPane.showMessageDialog(null, "Payment successful!", "Payment Confirmation", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            }
        });

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private boolean isValidCardNumber(String cardNumber) {
        return cardNumber.matches("\\d{16}");
    }

    private boolean isValidExpiryDate(String expiryDate) {
        return expiryDate.matches("(0[1-9]|1[0-2])\\/\\d{2}");
    }

    private boolean isValidCVV(String cvv) {
        return cvv.matches("\\d{3}");
    }
}
