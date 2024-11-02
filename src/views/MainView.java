package views;

import controllers.MenuController;
import controllers.OrderController;
import models.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MainView extends JFrame {
    private JLabel userNameLabel = new JLabel("", SwingConstants.CENTER);
    private JButton logoutButton = new JButton("Log out");

    private MenuController menuController;
    private OrderController orderController;

    public MainView(String userName, User user) {
        setTitle("Main Application");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.menuController = new MenuController();
        this.orderController = new OrderController(user);

        JTabbedPane tabbedPane = new JTabbedPane();

        // Cart Tab
        CartView cartView = new CartView(orderController, user); // Создаем CartView и передаем User

        // Menu Tab
        MenuView menuView = new MenuView(menuController, orderController, cartView); // Передаем CartView в MenuView
        tabbedPane.add("Menu", new JScrollPane(menuView)); // Добавляем MenuView в вкладку "Menu"

        // Добавляем CartView во вкладку "Cart"
        tabbedPane.add("Cart", cartView);

        // Log Out Tab
        JPanel logoutPanel = new JPanel(new BorderLayout());
        userNameLabel.setText(userName);
        logoutPanel.add(userNameLabel, BorderLayout.CENTER);
        logoutPanel.add(logoutButton, BorderLayout.SOUTH);
        tabbedPane.add("Log out", logoutPanel);

        add(tabbedPane);
        setLocationRelativeTo(null);
    }

    public void addLogoutListener(ActionListener listener) {
        logoutButton.addActionListener(listener);
    }
}
