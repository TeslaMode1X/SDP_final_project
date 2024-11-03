package views;

import controllers.MenuController;
import controllers.OrderController;
import models.OrderDetails;
import models.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class MainView extends JFrame {
    private JLabel userNameLabel = new JLabel("", SwingConstants.CENTER);
    private JButton logoutButton = new JButton("Log out");
    private List<OrderDetails> orderHistory = new ArrayList<>(); // Инициализация истории заказов

    public MainView(String userName, User user) {
        setTitle("Main Application");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        MenuController menuController = new MenuController();
        OrderController orderController = new OrderController(user);

        JTabbedPane tabbedPane = new JTabbedPane();

        // Создание CartView с передачей orderHistory
        CartView cartView = new CartView(orderController, user, orderHistory);

        // Menu Tab
        MenuView menuView = new MenuView(menuController, orderController, cartView);
        tabbedPane.add("Menu", new JScrollPane(menuView));

        // Добавление CartView во вкладку "Cart"
        tabbedPane.add("Cart", cartView);

        // Добавление HistoryView во вкладку "History"
        HistoryView historyView = new HistoryView(orderHistory);
        tabbedPane.add("History", historyView);

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
