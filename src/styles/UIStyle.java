package styles;

import javax.swing.*;
import java.awt.*;

// reusable styles for views
public class UIStyle {

    public static void styleTextField(JTextField textField) {
        textField.setFont(new Font("Arial", Font.PLAIN, 14));
        textField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        textField.setBackground(Color.WHITE);
        textField.setForeground(Color.DARK_GRAY);
    }

    public static void stylePasswordField(JPasswordField passwordField) {
        passwordField.setFont(new Font("Arial", Font.PLAIN, 14));
        passwordField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        passwordField.setBackground(Color.WHITE);
        passwordField.setForeground(Color.DARK_GRAY);
    }

    public static void styleButton(JButton button) {
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setBackground(new Color(70, 130, 180));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
    }

    // alert messages styles
    public static void showStyledMessage(Component parent, String message, String title) {
        // Create a custom JOptionPane
        JOptionPane optionPane = new JOptionPane(message, JOptionPane.INFORMATION_MESSAGE);

        // Create a dialog
        JDialog dialog = optionPane.createDialog(parent, title);

        // Set dialog font
        dialog.setFont(new Font("Arial", Font.PLAIN, 14));

        // Customize dialog properties
        dialog.setBackground(new Color(255, 255, 255)); // Background color

        // Create a custom panel for the message
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(new Color(255, 255, 255));

        // Add the message label with custom styles
        JLabel messageLabel = new JLabel(message);
        messageLabel.setFont(new Font("Arial", Font.PLAIN, 14)); // Change font size and style
        messageLabel.setForeground(new Color(70, 130, 180)); // Custom text color
        messageLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Padding
        panel.add(messageLabel, BorderLayout.CENTER);

        // Create and add OK button with custom styling
        JButton okButton = new JButton("OK");
        UIStyle.styleButton(okButton); // Use your existing button style method
        okButton.addActionListener(e -> dialog.dispose());

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(255, 255, 255));
        buttonPanel.add(okButton);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        dialog.setContentPane(panel); // Set the custom panel as the content
        dialog.setModal(true); // Make the dialog modal
        dialog.pack(); // Adjust size to fit the content

        // Center the dialog on the screen
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width - dialog.getWidth()) / 2;
        int y = (screenSize.height - dialog.getHeight()) / 2;
        dialog.setLocation(x, y); // Set dialog location to center of screen

        dialog.setVisible(true); // Show the dialog
    }

    public static void styleHistoryView(JPanel historyPanel) {
        historyPanel.setBackground(Color.WHITE);
        JLabel titleLabel = new JLabel("Order History", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setForeground(new Color(70, 130, 180));
        historyPanel.add(titleLabel, BorderLayout.NORTH);
    }
}
