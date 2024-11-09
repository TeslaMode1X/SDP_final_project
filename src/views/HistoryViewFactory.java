package views;

import java.util.List;
import models.OrderDetails;

public interface HistoryViewFactory {
    HistoryView createHistoryView(String username, List<OrderDetails> orderHistory);
}
