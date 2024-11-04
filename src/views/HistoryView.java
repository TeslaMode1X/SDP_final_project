package views;
import models.OrderDetails;
import javax.swing.*;
import java.awt.*;
import java.util.List;

import styles.UIStyle; // Import the UIStyle class for styling

public class HistoryView extends JPanel {
    private JTextArea itemTextArea;
    private JScrollPane scrollPane;

    public HistoryView(String username, List<OrderDetails> orderHistory) {
        setLayout(new BorderLayout()); // Set the layout manager

        // Call the styleHistoryView method to apply styles
        UIStyle.styleHistoryView(this);

        // Create a title label with username
        JLabel userLabel = new JLabel("Order History for: " + username);
        userLabel.setFont(new Font("Arial", Font.BOLD, 16));
        userLabel.setForeground(new Color(70, 130, 180));
        userLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(userLabel, BorderLayout.NORTH); // Add username label to the north

        itemTextArea = new JTextArea();
        itemTextArea.setEditable(false); // Make it non-editable
        itemTextArea.setLineWrap(true); // Enable line wrapping
        itemTextArea.setWrapStyleWord(true); // Wrap at word boundaries
        itemTextArea.setFont(new Font("Arial", Font.PLAIN, 14)); // Set font for text area
        itemTextArea.setForeground(Color.DARK_GRAY); // Set text color
        itemTextArea.setBackground(Color.WHITE); // Set background color

        // Set a border around the text area
        itemTextArea.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));

        scrollPane = new JScrollPane(itemTextArea);
        scrollPane.setBorder(BorderFactory.createEmptyBorder()); // Remove default border

        add(scrollPane, BorderLayout.CENTER); // Add the scroll pane to the center

        // Populate the text area with OrderDetails
        updateHistory(orderHistory);

        revalidate(); // Ensure layout is updated
        repaint(); // Ensure the UI is painted
    }

    public void updateHistory(List<OrderDetails> orderHistory) {
        StringBuilder sb = new StringBuilder(); // StringBuilder for efficient string concatenation

        // Iterate through orderHistory and append item details to StringBuilder
        for (OrderDetails order : orderHistory) {
            sb.append(order.toString()).append("\n"); // Add spacing between orders
        }

        // Append new orders on top of the old ones
        if (!orderHistory.isEmpty()) {
            itemTextArea.append(sb.toString());
        }

        itemTextArea.setCaretPosition(0);
    }

}
