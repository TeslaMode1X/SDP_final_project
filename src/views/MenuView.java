package views;

import controllers.MenuController;
import controllers.OrderController;
import models.MenuCategory;
import models.MenuItem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

import styles.UIStyle;

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
        setBackground(Color.WHITE); // Set background color

        // Panel for category filters
        JPanel filterPanel = new JPanel();
        filterPanel.setLayout(new FlowLayout());
        filterPanel.setBackground(Color.WHITE); // Set background color for filter panel

        // Initialize checkboxes for categories
        drinksCheckbox = new JCheckBox("Drinks", true);
        snacksCheckbox = new JCheckBox("Snacks", true);
        burgersCheckbox = new JCheckBox("Burgers", true);
        combosCheckbox = new JCheckBox("Combos", true);

        // Style checkboxes
        drinksCheckbox.setFont(new Font("Arial", Font.PLAIN, 14));
        snacksCheckbox.setFont(new Font("Arial", Font.PLAIN, 14));
        burgersCheckbox.setFont(new Font("Arial", Font.PLAIN, 14));
        combosCheckbox.setFont(new Font("Arial", Font.PLAIN, 14));

        // Sort button with styled action listener
        JButton sortButton = new JButton("Sort");
        UIStyle.styleButton(sortButton); // Apply button styles
        sortButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayMenu();
            }
        });

        // Add components to the filter panel
        filterPanel.add(drinksCheckbox);
        filterPanel.add(snacksCheckbox);
        filterPanel.add(burgersCheckbox);
        filterPanel.add(combosCheckbox);
        filterPanel.add(sortButton);

        add(filterPanel, BorderLayout.NORTH);

        // Panel for menu items
        menuItemsPanel = new JPanel();
        menuItemsPanel.setLayout(new BoxLayout(menuItemsPanel, BoxLayout.Y_AXIS));
        menuItemsPanel.setBackground(Color.WHITE); // Set background color for menu items
        add(menuItemsPanel, BorderLayout.CENTER);

        displayMenu(); // Display menu items on initialization
    }

    private void displayMenu() {
        menuItemsPanel.removeAll(); // Clear existing items

        for (Object categoryObj : menuController.getMainMenu().getItems()) {
            if (categoryObj instanceof MenuCategory) {
                MenuCategory category = (MenuCategory) categoryObj;

                // Check if the category is selected
                if ((category.getName().equals("Drinks") && drinksCheckbox.isSelected()) ||
                        (category.getName().equals("Snacks") && snacksCheckbox.isSelected()) ||
                        (category.getName().equals("Burgers") && burgersCheckbox.isSelected()) ||
                        (category.getName().equals("Combos") && combosCheckbox.isSelected())) {

                    JLabel categoryLabel = new JLabel("Category: " + category.getName());
                    categoryLabel.setFont(new Font("Arial", Font.BOLD, 16));
                    categoryLabel.setForeground(new Color(70, 130, 180)); // Set category label color
                    menuItemsPanel.add(categoryLabel);

                    for (Object itemObj : category.getItems()) {
                        if (itemObj instanceof MenuItem) {
                            MenuItem menuItem = (MenuItem) itemObj;

                            JPanel itemPanel = new JPanel(new BorderLayout());
                            itemPanel.setBackground(Color.WHITE); // Set background color for item panel
                            JLabel itemLabel = new JLabel(menuItem.getName() + " - $" + menuItem.getPrice());
                            itemLabel.setFont(new Font("Arial", Font.PLAIN, 14)); // Set item label font

                            JButton addToCartButton = new JButton("Add to Cart");
                            UIStyle.styleButton(addToCartButton); // Apply button styles

                            // Action listener for adding item to cart
                            addToCartButton.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    orderController.addItemToCart(menuItem);
                                    UIStyle.showStyledMessage(MenuView.this, menuItem.getName() + " added to cart!", "Information");
                                    cartView.updateCartDisplay();
                                }
                            });

                            itemPanel.add(itemLabel, BorderLayout.CENTER);
                            itemPanel.add(addToCartButton, BorderLayout.EAST);
                            menuItemsPanel.add(itemPanel);
                        }
                    }
                    menuItemsPanel.add(Box.createVerticalStrut(15)); // Add spacing between categories
                }
            }
        }
        revalidate();
        repaint(); // Refresh the panel to display changes
    }
}
