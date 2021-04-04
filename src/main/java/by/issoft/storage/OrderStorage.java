package by.issoft.storage;

import by.issoft.domain.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static by.issoft.serializator.OrderIDWriterReader.*;
import static by.issoft.serializator.SerializatorOrders.*;

public class OrderStorage {
    private static int countOrders = 0;
    Logger logger = LoggerFactory.getLogger(OrderStorage.class);

    //return filepath of Order
    public String findOrderFilePathByID(String id) {
        String filepath = null;

        logger.debug("Method findOrderFilePathByID(String id) call readAllIDs()");
        List<String> allOrdersIDs = readAllIDs();

        for (String orderID : allOrdersIDs) {
            if (orderID.equals(id)) {
                return "./src/main/resources/orders_data/" + orderID + ".txt";
            }
        }
        logger.debug("Order with id " + id + " not found.");
        return filepath;
    }

    public String saveOrder(Order order) {
        boolean success;
        countOrders++;
        serialization(order);
        success = writeNewOrderIDToFile(order);
        if (success) {
            logger.debug("Order " + order.getOrderId() + " successfully saved.");
            return order.getOrderId().toString();
        } else {
            logger.debug("Something wrong. Order was not saved: " +success);
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
    //not used
    public boolean deleteOrder(Order order) {
        if (countOrders > 0) {
            countOrders--;
            logger.debug("Successfully deleted order " + order.getOrderId() + " from DB.");
            return true;
        } else {
            logger.debug("Order #" + order.getOrderId() + " not found in DB. Unsuccessful operation.");
            return false;
        }

    }


}
