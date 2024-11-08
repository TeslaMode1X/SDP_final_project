package views;

import controllers.UserActionObserver;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

import styles.UIStyle;

public class UserView extends JFrame {
    private JTextField emailField = new JTextField(20);
    private JTextField nameField = new JTextField(20);
    private JPasswordField passwordField = new JPasswordField(20);
    private JButton loginButton = new JButton("Login");
    private JButton registerButton = new JButton("Register");

    // List of observers
    private List<UserActionObserver> observers = new ArrayList<>();

    public UserView() {
        setTitle("User Login & Registration");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel for title and labels
        JPanel headerPanel = new JPanel(new FlowLayout());
        JLabel titleLabel = new JLabel("Fast&Yummy");

        JLabel email = new JLabel("Email:");
        JLabel password = new JLabel("Password:");
        JLabel name = new JLabel("Name (for registration):");

        // Adding fonts to app labels
        try {
            Font customFontBig = Font.createFont(Font.TRUETYPE_FONT, Objects.requireNonNull(getClass().getResourceAsStream("/fonts/Quicksand/static/Quicksand-Bold.ttf"))).deriveFont(28f);
            Font customFontMedium = Font.createFont(Font.TRUETYPE_FONT, Objects.requireNonNull(getClass().getResourceAsStream("/fonts/Quicksand/static/Quicksand-Medium.ttf"))).deriveFont(20f);
            titleLabel.setFont(customFontBig);

            email.setFont(customFontMedium);
            password.setFont(customFontMedium);
            name.setFont(customFontMedium);
        } catch (FontFormatException | IOException e) {
            titleLabel.setFont(new Font("Serif", Font.BOLD, 28)); // Fallback font if custom font fails
        }

        headerPanel.add(titleLabel);
        headerPanel.add(Box.createRigidArea(new Dimension(0, 50))); // Add vertical margin under title

        JPanel panel = new JPanel(new GridLayout(4, 2, 20, 10));
        panel.setPreferredSize(new Dimension(500, 200));

        UIStyle.styleTextField(emailField);
        UIStyle.styleTextField(nameField);
        UIStyle.stylePasswordField(passwordField);
        UIStyle.styleButton(loginButton);
        UIStyle.styleButton(registerButton);

        panel.add(email);
        panel.add(emailField);
        panel.add(password);
        panel.add(passwordField);
        panel.add(name);
        panel.add(nameField);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(loginButton);
        buttonPanel.add(registerButton);

        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        contentPanel.add(panel, BorderLayout.CENTER);
        contentPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(headerPanel, BorderLayout.NORTH);
        add(contentPanel, BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);

        // Add listeners for login and register buttons that notify observers
        loginButton.addActionListener(e -> notifyLoginObservers());
        registerButton.addActionListener(e -> notifyRegisterObservers());
    }

    // Methods for notifying observers of login and registration actions
    public void addObserver(UserActionObserver observer) {
        observers.add(observer);
    }

    public void notifyLoginObservers() {
        String email = getEmail();
        String password = getPassword();
        for (UserActionObserver observer : observers) {
            observer.onLogin(email, password);
        }
    }

    public void notifyRegisterObservers() {
        String email = getEmail();
        String password = getPassword();
        String name = getName();
        for (UserActionObserver observer : observers) {
            observer.onRegister(email, password, name);
        }
    }

    // Getter methods for user input
    public String getEmail() {
        return emailField.getText();
    }

    public String getPassword() {
        return new String(passwordField.getPassword());
    }

    public String getName() {
        return nameField.getText();
    }

    public void showMessage(String message) {
        UIStyle.showStyledMessage(this, message, "Information");
    }
}
