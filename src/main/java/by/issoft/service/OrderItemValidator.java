package by.issoft.service;

import by.issoft.domain.OrderItem;

public class OrderItemValidator {

    public boolean isValidItem(OrderItem item) {
        if (item.getName() == null) {
            return false;
        }
        if (item.getTotalCost() < 0) {
            return false;
        }
        return true;
    }
}
