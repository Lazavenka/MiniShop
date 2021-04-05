package by.issoft.storage;

import by.issoft.domain.OrderItem;
import by.issoft.service.OrderItemValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static by.issoft.serializator.SerializatorOrderItems.deserialization;
import static by.issoft.serializator.SerializatorOrderItems.serialization;

public class OrderItemStorage {
    private final Logger logger = LoggerFactory.getLogger(OrderItemStorage.class);

    private final OrderItemValidator orderItemValidator = new OrderItemValidator();

    private final String path = "./src/main/resources/orderItems_data/";

    public String saveOrderItem(OrderItem orderItem) {
        String orderID = null;
        if (orderItemValidator.isValidItem(orderItem)) {
            orderID = orderItem.getItemID().toString();
            serialization(orderItem);
            logger.debug("Order item " + orderID + " successfully saved to storage.");
        } else {
            logger.debug("Order item is invalid. Can't save to storage.");
        }
        return orderID;
    }

    public OrderItem loadOrderItem(String orderItemID) {
        OrderItem orderItem;
        orderItem = deserialization(getPathFromID(orderItemID));
        if (orderItem != null) {
            logger.debug("Order item " + orderItemID + " successfully loaded!");
            return orderItem;
        } else {
            logger.debug("Something wrong. Order item " + orderItemID + " not loaded!");
            return null;
        }
    }

    private String getPathFromID(String orderItemID) {
        return path + orderItemID + ".txt";
    }
}
