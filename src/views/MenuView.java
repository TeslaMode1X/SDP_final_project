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
    private CartView cartView; // Ссылка на CartView

    public MenuView(MenuController menuController, OrderController orderController, CartView cartView) {
        this.menuController = menuController;
        this.orderController = orderController;
        this.cartView = cartView;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS)); // Выравнивание по вертикали
        displayMenu();
    }

    private void displayMenu() {
        for (Object categoryObj : menuController.getMainMenu().getItems()) {
            if (categoryObj instanceof MenuCategory) {
                MenuCategory category = (MenuCategory) categoryObj;

                // Заголовок для категории
                JLabel categoryLabel = new JLabel("Category: " + category.getName());
                categoryLabel.setFont(new Font("Arial", Font.BOLD, 16));
                add(categoryLabel);

                // Отображение позиций в категории
                for (Object itemObj : category.getItems()) {
                    if (itemObj instanceof MenuItem) {
                        MenuItem menuItem = (MenuItem) itemObj;

                        // Панель для каждого элемента меню
                        JPanel itemPanel = new JPanel(new BorderLayout());
                        JLabel itemLabel = new JLabel(menuItem.getName() + " - $" + menuItem.getPrice());
                        JButton addToCartButton = new JButton("Add to Cart");

                        // Добавляем действие к кнопке
                        addToCartButton.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                orderController.addItemToCart(menuItem); // Добавляем товар в корзину
                                JOptionPane.showMessageDialog(null, menuItem.getName() + " added to cart!");
                                cartView.updateCartDisplay(); // Обновляем отображение корзины
                            }
                        });

                        itemPanel.add(itemLabel, BorderLayout.CENTER);
                        itemPanel.add(addToCartButton, BorderLayout.EAST);
                        add(itemPanel);
                    }
                }
                add(Box.createVerticalStrut(15)); // Добавляем вертикальный отступ между категориями
            }
        }
    }
}
