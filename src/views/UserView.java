package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import styles.UIStyle;

public class UserView extends JFrame {
    private JTextField emailField = new JTextField(20);
    private JTextField nameField = new JTextField(20);
    private JPasswordField passwordField = new JPasswordField(20);
    private JButton loginButton = new JButton("Login");
    private JButton registerButton = new JButton("Register");

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
            Font customFontBig = Font.createFont(Font.TRUETYPE_FONT, new File("src/fonts/Quicksand/static/Quicksand-Bold.ttf")).deriveFont(28f);
            Font customFontMedium = Font.createFont(Font.TRUETYPE_FONT, new File("src/fonts/Quicksand/static/Quicksand-Medium.ttf")).deriveFont(20f);
            titleLabel.setFont(customFontBig);

            email.setFont(customFontMedium);
            password.setFont(customFontMedium);
            name.setFont(customFontMedium);
        } catch (FontFormatException | IOException e) {
            // Default fonts if styles not found
            titleLabel.setFont(new Font("Serif", Font.BOLD, 28));
        }

        // Adding title and margin under it
        headerPanel.add(titleLabel);
        headerPanel.add(Box.createRigidArea(new Dimension(0, 50))); // Add vertical margin under title

        // Panel for user input fields
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2, 20, 10));
        panel.setPreferredSize(new Dimension(500, 200));

        // Applying styles from UIStyle
        UIStyle.styleTextField(emailField);
        UIStyle.styleTextField(nameField);
        UIStyle.stylePasswordField(passwordField);
        UIStyle.styleButton(loginButton);
        UIStyle.styleButton(registerButton);

        // Adding labels and inputs to panel
        panel.add(email);
        panel.add(emailField);

        panel.add(password);
        panel.add(passwordField);

        panel.add(name);
        panel.add(nameField);

        // Panel for buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(loginButton);
        buttonPanel.add(registerButton);

        // Main content panel with input and button panels, with margin added
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Add 20-pixel margin around the content
        contentPanel.add(panel, BorderLayout.CENTER);
        contentPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Adding header (with title and margin) to the top of the frame
        add(headerPanel, BorderLayout.NORTH);
        // Adding content panel (inputs and buttons) to the center of the frame
        add(contentPanel, BorderLayout.CENTER);

        // Adjusts the window to fit preferred size of all components
        pack();

        // Centers the window on the screen
        setLocationRelativeTo(null);
    }

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

    public void addLoginListener(ActionListener listener) {
        loginButton.addActionListener(listener);
    }

    public void addRegisterListener(ActionListener listener) {
        registerButton.addActionListener(listener);
    }
}
