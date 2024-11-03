package views;

import controllers.OrderController;
import models.MenuItem;
import models.OrderDetails;
import models.User;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class CartView extends JPanel {
    private final OrderController orderController;
    private final List<OrderDetails> orderHistory;
    private JPanel cartItemsPanel;
    private JLabel totalLabel;
    private JButton checkoutButton;

    public CartView(OrderController orderController, User user, List<OrderDetails> orderHistory) {
        this.orderController = orderController;
        this.orderHistory = orderHistory;

        setLayout(new BorderLayout());

        cartItemsPanel = new JPanel();
        cartItemsPanel.setLayout(new BoxLayout(cartItemsPanel, BoxLayout.Y_AXIS));
        totalLabel = new JLabel("Total: $0.0");

        checkoutButton = new JButton("Checkout");
        checkoutButton.addActionListener(e -> checkout());

        add(new JScrollPane(cartItemsPanel), BorderLayout.CENTER);
        add(totalLabel, BorderLayout.SOUTH);
        add(checkoutButton, BorderLayout.NORTH);

        updateCartDisplay();
    }

    public void updateCartDisplay() {
        cartItemsPanel.removeAll();
        List<MenuItem> items = orderController.getCart().getItems();
        double total = 0;

        for (MenuItem item : items) {
            JPanel itemPanel = new JPanel(new BorderLayout());
            JLabel itemLabel = new JLabel(item.getName() + " - $" + item.getPrice());
            JButton removeButton = new JButton("Remove");

            removeButton.addActionListener(e -> {
                orderController.removeItemFromCart(item);
                updateCartDisplay();
            });

            itemPanel.add(itemLabel, BorderLayout.CENTER);
            itemPanel.add(removeButton, BorderLayout.EAST);
            cartItemsPanel.add(itemPanel);

            total += item.getPrice();
        }

        totalLabel.setText("Total: $" + total);
        revalidate();
        repaint();
    }

    public void checkout() {
        double total = orderController.checkout();
        new PaymentSelectionView(total, orderHistory);
        updateCartDisplay();
    }
}
