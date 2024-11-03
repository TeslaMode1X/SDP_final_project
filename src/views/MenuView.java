package views;

import controllers.MenuController;
import controllers.OrderController;
import models.MenuCategory;
import models.MenuItem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuView extends JPanel {
    private MenuController menuController;
    private OrderController orderController;
    private CartView cartView;

    private JCheckBox drinksCheckbox;
    private JCheckBox snacksCheckbox;
    private JCheckBox burgersCheckbox;
    private JCheckBox combosCheckbox;
    private JPanel menuItemsPanel;

    public MenuView(MenuController menuController, OrderController orderController, CartView cartView) {
        this.menuController = menuController;
        this.orderController = orderController;
        this.cartView = cartView;

        setLayout(new BorderLayout());

        // Панель фильтров для категорий
        JPanel filterPanel = new JPanel();
        filterPanel.setLayout(new FlowLayout());

        drinksCheckbox = new JCheckBox("Drinks", true);
        snacksCheckbox = new JCheckBox("Snacks", true);
        burgersCheckbox = new JCheckBox("Burgers", true);
        combosCheckbox = new JCheckBox("Combos", true);

        JButton sortButton = new JButton("Sort");
        sortButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayMenu();
            }
        });

        filterPanel.add(drinksCheckbox);
        filterPanel.add(snacksCheckbox);
        filterPanel.add(burgersCheckbox);
        filterPanel.add(combosCheckbox);
        filterPanel.add(sortButton);

        add(filterPanel, BorderLayout.NORTH);

        menuItemsPanel = new JPanel();
        menuItemsPanel.setLayout(new BoxLayout(menuItemsPanel, BoxLayout.Y_AXIS));
        add(menuItemsPanel, BorderLayout.CENTER);

        displayMenu();
    }

    private void displayMenu() {
        menuItemsPanel.removeAll();

        for (Object categoryObj : menuController.getMainMenu().getItems()) {
            if (categoryObj instanceof MenuCategory) {
                MenuCategory category = (MenuCategory) categoryObj;

                if ((category.getName().equals("Drinks") && drinksCheckbox.isSelected()) ||
                        (category.getName().equals("Snacks") && snacksCheckbox.isSelected()) ||
                        (category.getName().equals("Burgers") && burgersCheckbox.isSelected()) ||
                        (category.getName().equals("Combos") && combosCheckbox.isSelected())) {

                    JLabel categoryLabel = new JLabel("Category: " + category.getName());
                    categoryLabel.setFont(new Font("Arial", Font.BOLD, 16));
                    menuItemsPanel.add(categoryLabel);

                    for (Object itemObj : category.getItems()) {
                        if (itemObj instanceof MenuItem) {
                            MenuItem menuItem = (MenuItem) itemObj;

                            JPanel itemPanel = new JPanel(new BorderLayout());
                            JLabel itemLabel = new JLabel(menuItem.getName() + " - $" + menuItem.getPrice());
                            JButton addToCartButton = new JButton("Add to Cart");

                            addToCartButton.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    orderController.addItemToCart(menuItem);
                                    JOptionPane.showMessageDialog(null, menuItem.getName() + " added to cart!");
                                    cartView.updateCartDisplay();
                                }
                            });

                            itemPanel.add(itemLabel, BorderLayout.CENTER);
                            itemPanel.add(addToCartButton, BorderLayout.EAST);
                            menuItemsPanel.add(itemPanel);
                        }
                    }
                    menuItemsPanel.add(Box.createVerticalStrut(15));
                }
            }
        }
        revalidate();
        repaint();
    }
}
