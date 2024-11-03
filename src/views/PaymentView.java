package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PaymentView extends JFrame {
    private JLabel totalAmountLabel;
    private JTextField cardNumberField;
    private JTextField expiryDateField;
    private JTextField cvvField;
    private JButton payButton;

    public PaymentView(double totalAmount) {
        setTitle("Payment Window");
        setSize(400, 300);
        setLayout(new GridLayout(5, 2, 10, 10));
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

        payButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Placeholder logic for payment processing
                if (cardNumberField.getText().isEmpty() || expiryDateField.getText().isEmpty() || cvvField.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please fill in all payment details.", "Payment Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Payment successful!", "Payment Confirmation", JOptionPane.INFORMATION_MESSAGE);
                    dispose(); // Close the payment window
                }
            }
        });

        setLocationRelativeTo(null);
        setVisible(true);
    }
}
