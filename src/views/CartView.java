package views;

import controllers.OrderController;
import models.MenuItem;
import models.OrderDetails;
import models.User;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CartView extends JPanel {
    private final OrderController orderController;
    private JPanel cartItemsPanel;
    private JLabel totalLabel;
    private JButton checkoutButton;
    private HistoryView historyView;

    public CartView(OrderController orderController, User user, HistoryView historyView) {
        this.orderController = orderController;
        this.historyView = historyView;

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
        if (orderController.getCart().getItems().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Your cart is empty. Please add items before checking out.", "Empty Cart", JOptionPane.WARNING_MESSAGE);
        } else {
            double total = orderController.checkout();
            List<MenuItem> cartItems = orderController.getCart().getItems();

            // Create an OrderDetails object with the items in the cart
            OrderDetails orderDetails = new OrderDetails(total, "", new ArrayList<>(cartItems));

            // Pass the order details to PaymentSelectionView
            new PaymentSelectionView(total, orderDetails, historyView);

            orderController.getCart().clearCart();

            updateCartDisplay();
        }
    }

}
