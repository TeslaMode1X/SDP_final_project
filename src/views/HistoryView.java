package views;

import models.OrderDetails;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class HistoryView extends JPanel {
    private JList<String> orderList;

    public HistoryView(List<OrderDetails> orderHistory) {
        setLayout(new BorderLayout());
        DefaultListModel<String> listModel = new DefaultListModel<>();
        for (OrderDetails order : orderHistory) {
            listModel.addElement(order.toString()); // Отображаем каждый заказ в списке
        }
        orderList = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(orderList);
        add(scrollPane, BorderLayout.CENTER);
    }

    // Метод для обновления истории
    public void updateHistory(List<OrderDetails> orderHistory) {
        DefaultListModel<String> listModel = new DefaultListModel<>();
        for (OrderDetails order : orderHistory) {
            listModel.addElement(order.toString());
        }
        orderList.setModel(listModel);
    }
}
