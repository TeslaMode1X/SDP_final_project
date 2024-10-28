package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MainView extends JFrame {
    private JLabel userNameLabel = new JLabel("", SwingConstants.CENTER);
    private JButton logoutButton = new JButton("Log out");

    public MainView(String userName) {
        setTitle("Main Application");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTabbedPane tabbedPane = new JTabbedPane();

        // Add Order Tab
        JPanel addOrderPanel = new JPanel();
        tabbedPane.add("Add Order", addOrderPanel);

        // Log Out Tab
        JPanel logoutPanel = new JPanel(new BorderLayout());
        userNameLabel.setText(userName);  // Set the username in the center
        logoutPanel.add(userNameLabel, BorderLayout.CENTER);
        logoutPanel.add(logoutButton, BorderLayout.SOUTH);
        tabbedPane.add("Log out", logoutPanel);

        add(tabbedPane);
    }

    public void addLogoutListener(ActionListener listener) {
        logoutButton.addActionListener(listener);
    }
}