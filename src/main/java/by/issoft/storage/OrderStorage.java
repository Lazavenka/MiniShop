package by.issoft.storage;

import by.issoft.domain.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static by.issoft.serializator.OrderIDWriterReader.*;
import static by.issoft.serializator.SerializatorOrders.*;

public class OrderStorage {
    Logger logger = LoggerFactory.getLogger(OrderStorage.class);

    public String findOrderFilePathByID(String id) {
        String path = "./src/main/resources/orders_data/";
        return path + id + ".txt";
    }

    public String saveOrder(Order order) {
        boolean success;
        serialization(order);
        success = writeNewOrderIDToFile(order);
        if (success) {
            logger.debug("Order " + order.getOrderId() + " successfully saved.");
            return order.getOrderId().toString();
        } else {
            logger.debug("Something wrong. Order was not saved: " + success);
            return null;
        }
    }

    public Order loadOrder(String id) {
        Order order;
        order = deserialization(findOrderFilePathByID(id));
        if (order != null) {
            logger.debug("Order " + id + " successful loaded!");
        } else {
            logger.debug("Order is not load.");
        }
        return order;
    }
}
