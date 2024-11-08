package views;

import controllers.MenuController;
import controllers.OrderController;
import models.OrderDetails;
import models.OrderHistoryManager;
import models.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

public class MainView extends JFrame {
    private JLabel userNameLabel = new JLabel("", SwingConstants.CENTER);
    private JButton logoutButton = new JButton("Log out");

    public MainView(String userName, User user) {

        setTitle("Main Application");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        MenuController menuController = new MenuController();
        OrderController orderController = new OrderController(user);

        JTabbedPane tabbedPane = new JTabbedPane();
        styleTabbedPane(tabbedPane); // Apply button styles to the tabs

        // Access the global order history from OrderHistoryManager
        List<OrderDetails> globalOrderHistory = OrderHistoryManager.getInstance().getOrderHistory();

        // Create HistoryView with the persistent order history
        HistoryView historyView = new HistoryView(userName, globalOrderHistory);

        // Creating CartView with orderController and passing historyView
        CartView cartView = new CartView(orderController, user, historyView);

        // Menu Tab
        MenuView menuView = new MenuView(menuController, orderController, cartView);
        JScrollPane menuScrollPane = new JScrollPane(menuView);
        menuScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        tabbedPane.add("Menu", menuScrollPane);

        // Adding CartView to the "Cart" tab
        tabbedPane.add("Cart", cartView);

        // Adding HistoryView to the "History" tab
        tabbedPane.add("History", historyView);

        // Log Out Tab
        LogoutView logoutView = new LogoutView(userName);
        logoutView.setLogoutButton(logoutButton);
        tabbedPane.add("Log out", logoutView);

        add(tabbedPane);
        setLocationRelativeTo(null);
    }

    private void styleTabbedPane(JTabbedPane tabbedPane) {
        tabbedPane.setBackground(new Color(70, 130, 180));
        tabbedPane.setForeground(Color.WHITE);
        tabbedPane.setFont(new Font("Arial", Font.BOLD, 14));
        tabbedPane.setOpaque(true);
        tabbedPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        for (int i = 0; i < tabbedPane.getTabCount(); i++) {
            tabbedPane.setBackgroundAt(i, new Color(70, 130, 180));
            tabbedPane.setForegroundAt(i, Color.WHITE);
        }
    }

    public void addLogoutListener(ActionListener listener) {
        logoutButton.addActionListener(listener);
    }
}
