package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import models.MenuItem;
import models.OrderDetails;
import java.util.List;
import views.*;

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

        totalAmountLabel = new JLabel("Total Amount: $" + totalAmount);
        cardNumberField = new JTextField(16);
        expiryDateField = new JTextField(5);
        cvvField = new JTextField(3);
        payButton = new JButton("Pay Now");

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
                historyView.updateHistory(List.of(orderDetails)); // Assuming this updates the history
                JOptionPane.showMessageDialog(null, "Payment successful!", "Payment Confirmation", JOptionPane.INFORMATION_MESSAGE);
                dispose(); // Close the payment window
            }
        });

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private boolean isValidCardNumber(String cardNumber) {
        // Valid if it is 16 digits
        return cardNumber.matches("\\d{16}");
    }

    private boolean isValidExpiryDate(String expiryDate) {
        // Validate the expiry date format MM/YY
        return expiryDate.matches("(0[1-9]|1[0-2])\\/\\d{2}");
    }

    private boolean isValidCVV(String cvv) {
        // Valid if it is 3 digits
        return cvv.matches("\\d{3}");
    }
}
