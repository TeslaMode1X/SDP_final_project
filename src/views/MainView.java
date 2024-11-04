package views;

import controllers.MenuController;
import controllers.OrderController;
import models.MenuItem;
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
    private List<OrderDetails> orderHistory = new ArrayList<>(); // Change to List<OrderDetails>

    public MainView(String userName, User user) {
        setTitle("Main Application");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        MenuController menuController = new MenuController();
        OrderController orderController = new OrderController(user);

        JTabbedPane tabbedPane = new JTabbedPane();

        // History
        HistoryView historyView = new HistoryView(userName, orderHistory); // Passing orderHistory as List<OrderDetails>

        // Creating CartView with orderController and user
        CartView cartView = new CartView(orderController, user, historyView);

        // Menu Tab
        MenuView menuView = new MenuView(menuController, orderController, cartView);
        JScrollPane menuScrollPane = new JScrollPane(menuView); // Wrap MenuView in JScrollPane
        menuScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        tabbedPane.add("Menu", menuScrollPane);

        // Adding CartView to the "Cart" tab
        tabbedPane.add("Cart", cartView);

        // Adding HistoryView to the "History" tab
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
