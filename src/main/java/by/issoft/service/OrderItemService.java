package by.issoft.service;

import by.issoft.domain.OrderItem;
import by.issoft.storage.OrderItemStorage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

public class OrderItemService {
    private final Logger logger = LoggerFactory.getLogger(OrderItemService.class);
    private final OrderItemStorage orderItemStorage;
    private final OrderItemValidator orderItemValidator;

    public OrderItemService(OrderItemStorage orderItemStorage,
                            OrderItemValidator orderItemValidator) {
        this.orderItemStorage = orderItemStorage;
        this.orderItemValidator = orderItemValidator;
    }

    public int getTotalCostOrderItem(UUID orderItemID) {
        OrderItem item = orderItemStorage.loadOrderItem(orderItemID.toString());
        return item.getTotalCost();
    }

    public String createOrderItem(OrderItem orderItem) {
        boolean valid = orderItemValidator.isValidItem(orderItem);
        if (!valid) {
            logger.debug("Order item " + orderItem.getItemID() + " is invalid. Can't create item.");
            return null;
        }
        logger.debug("Correct order item. Create item -> " + orderItem.getItemID());
        return orderItemStorage.saveOrderItem(orderItem);
    }
}
