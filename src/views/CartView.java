package views;

import controllers.OrderController;
import models.Cart;
import models.MenuItem;
import models.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CartView extends JPanel {
    private OrderController orderController;
    private JPanel cartItemsPanel; // Панель для отображения позиций в корзине
    private JLabel totalLabel; // Метка для отображения общей стоимости
    private User user;

    public CartView(OrderController orderController, User user) {
        this.orderController = orderController;
        this.user = user;

        setLayout(new BorderLayout());

        cartItemsPanel = new JPanel();
        cartItemsPanel.setLayout(new BoxLayout(cartItemsPanel, BoxLayout.Y_AXIS));

        totalLabel = new JLabel();
        updateCartDisplay(); // Отображаем начальное состояние корзины

        JButton checkoutButton = new JButton("Checkout");
        checkoutButton.addActionListener(e -> checkout());

        add(new JScrollPane(cartItemsPanel), BorderLayout.CENTER);
        add(totalLabel, BorderLayout.NORTH);
        add(checkoutButton, BorderLayout.SOUTH);
    }

    public void updateCartDisplay() {
        Cart cart = Cart.getInstance(user); // Получаем корзину пользователя
        cartItemsPanel.removeAll(); // Очищаем панель перед повторным добавлением элементов

        for (MenuItem item : cart.getItems()) {
            JPanel itemPanel = new JPanel(new BorderLayout());
            JLabel itemLabel = new JLabel(item.getName() + " - $" + item.getPrice());

            // Кнопка для удаления товара
            JButton removeButton = new JButton("X");
            removeButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    orderController.removeItemFromCart(item); // Удаляем товар из корзины
                    updateCartDisplay(); // Обновляем отображение корзины
                }
            });

            itemPanel.add(itemLabel, BorderLayout.CENTER);
            itemPanel.add(removeButton, BorderLayout.EAST);
            cartItemsPanel.add(itemPanel);
        }

        // Обновляем общую стоимость
        totalLabel.setText("Total: $" + cart.getTotalPrice());
        revalidate(); // Обновляем интерфейс
        repaint();
    }

    private void checkout() {
        double total = orderController.checkout();
        new PaymentView(total); // Открываем окно оплаты
        updateCartDisplay(); // Очищаем корзину после оформления заказа
    }

}
