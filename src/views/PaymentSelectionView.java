package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import models.OrderDetails;

public class PaymentSelectionView extends JFrame {
    private JButton payOnDeliveryButton;
    private JButton payByCardButton;
    private double totalAmount;
    private List<OrderDetails> orderHistory;

    public PaymentSelectionView(double totalAmount, List<OrderDetails> orderHistory) {
        this.totalAmount = totalAmount;
        this.orderHistory = orderHistory;

        setTitle("Select Payment Method");
        setSize(300, 150);
        setLayout(new GridLayout(2, 1, 10, 10));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        payOnDeliveryButton = new JButton("Pay on Delivery");
        payByCardButton = new JButton("Pay by Card");

        payOnDeliveryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Order received", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
                orderHistory.add(new OrderDetails(totalAmount, "Pay on Delivery")); // Добавляем запись в историю
                dispose(); // Закрываем окно выбора
            }
        });

        payByCardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new PaymentView(totalAmount, orderHistory); // Открываем окно оплаты картой
                dispose(); // Закрываем окно выбора
            }
        });

        add(payOnDeliveryButton);
        add(payByCardButton);

        setLocationRelativeTo(null);
        setVisible(true);
    }
}
