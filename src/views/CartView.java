package views;

import controllers.OrderController;
import models.MenuItem;
import models.OrderDetails;
import models.User;
import styles.UIStyle;

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
        setBackground(Color.WHITE); // Set background color

        // Initialize and style the cart items panel
        cartItemsPanel = new JPanel();
        cartItemsPanel.setLayout(new BoxLayout(cartItemsPanel, BoxLayout.Y_AXIS));
        cartItemsPanel.setBackground(Color.WHITE); // Background color for the items panel
        cartItemsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Padding

        // Initialize and style the total label
        totalLabel = new JLabel("Total: $0.0");
        totalLabel.setFont(new Font("Arial", Font.BOLD, 16));
        totalLabel.setForeground(new Color(70, 130, 180)); // Set custom color
        totalLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Padding

        // Initialize and style the checkout button
        checkoutButton = new JButton("Checkout");
        UIStyle.styleButton(checkoutButton); // Apply styles from UIStyle
        checkoutButton.setFont(new Font("Arial", Font.BOLD, 14)); // Custom font for button
        checkoutButton.setBackground(new Color(70, 130, 180)); // Set background color for button
        checkoutButton.setForeground(Color.WHITE); // Set text color for button
        checkoutButton.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Padding

        // Add action listener to checkout button
        checkoutButton.addActionListener(e -> checkout());

        // Create a scroll pane for the cart items
        JScrollPane scrollPane = new JScrollPane(cartItemsPanel);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Your Cart")); // Titled border for scroll pane

        // Add components to the CartView
        add(scrollPane, BorderLayout.CENTER);
        add(totalLabel, BorderLayout.SOUTH);
        add(checkoutButton, BorderLayout.NORTH);

        // Style the layout with margins and padding
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Outer padding

        updateCartDisplay(); // Initial display of cart items
    }

    public void updateCartDisplay() {
        cartItemsPanel.removeAll(); // Clear existing items
        List<MenuItem> items = orderController.getCart().getItems();
        double total = 0;

        for (MenuItem item : items) {
            // Create a fixed-size item panel
            JPanel itemPanel = new JPanel(new BorderLayout()); // Use BorderLayout for left-right alignment
            itemPanel.setPreferredSize(new Dimension(300, 40)); // Set a fixed size for item panels
            itemPanel.setBackground(Color.WHITE); // Background color for item panel
            itemPanel.setBorder(BorderFactory.createLineBorder(new Color(70, 130, 180), 1)); // Border for item panel

            JLabel itemLabel = new JLabel(item.getName() + " - $" + String.format("%.2f", item.getPrice()));
            itemLabel.setFont(new Font("Arial", Font.PLAIN, 14)); // Set item label font
            itemLabel.setForeground(new Color(70, 130, 180)); // Set item label color
            itemLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)); // Padding for item label

            JButton removeButton = new JButton("Remove");
            UIStyle.styleButton(removeButton); // Apply styles from UIStyle
            removeButton.setPreferredSize(new Dimension(100, 30)); // Set button size for "Remove" to fit properly
            removeButton.addActionListener(e -> {
                orderController.removeItemFromCart(item);
                updateCartDisplay(); // Refresh the display after removal
            });

            // Add components to item panel
            itemPanel.add(itemLabel, BorderLayout.WEST); // Align item label to the left
            itemPanel.add(removeButton, BorderLayout.EAST); // Align remove button to the right
            cartItemsPanel.add(itemPanel); // Add the item panel to the main panel

            total += item.getPrice(); // Update total
        }

        totalLabel.setText("Total: $" + String.format("%.2f", total)); // Update total amount

        revalidate();
        repaint(); // Refresh the panel to display changes
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

            orderController.getCart().clearCart(); // Clear cart after checkout

            updateCartDisplay(); // Update the display after checkout
        }
    }
}
