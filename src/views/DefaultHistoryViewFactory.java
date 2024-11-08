package views;

import java.util.List;
import models.OrderDetails;

public class DefaultHistoryViewFactory implements HistoryViewFactory {
    @Override
    public HistoryView createHistoryView(String username, List<OrderDetails> orderHistory) {
        return new HistoryView(username, orderHistory);
    }
}
