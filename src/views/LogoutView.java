package views;

import styles.UIStyle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class LogoutView extends JPanel {
    private JLabel userNameLabel = new JLabel("", SwingConstants.CENTER);
    private JButton logoutButton; // Declare button

    public LogoutView(String userName) {
        setLayout(new BorderLayout());

        // Set the username label with styles
        userNameLabel.setText("Logged in as: " + userName);
        userNameLabel.setFont(new Font("Arial", Font.PLAIN, 16)); // Set font size for label
        userNameLabel.setForeground(new Color(70, 130, 180)); // Set text color
        add(userNameLabel, BorderLayout.CENTER);
    }

    // Method to set the logout button
    public void setLogoutButton(JButton logoutButton) {
        this.logoutButton = logoutButton; // Store the logout button reference
        UIStyle.styleButton(logoutButton); // Apply styles to the logout button
        add(logoutButton, BorderLayout.SOUTH); // Add button to the panel
    }

    public void addLogoutListener(ActionListener listener) {
        if (logoutButton != null) { // Ensure button is set
            logoutButton.addActionListener(listener);
        }
    }
}
