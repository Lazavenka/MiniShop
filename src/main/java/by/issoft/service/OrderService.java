package by.issoft.service;

import by.issoft.domain.Order;
import by.issoft.domain.OrderItem;
import by.issoft.domain.OrderStatus;
import by.issoft.domain.User;
import by.issoft.serializator.OrderIDWriterReader;
import by.issoft.storage.OrderStorage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class OrderService {
    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);

    private final OrderStorage orderStorage;
    private final OrderItemValidator orderItemValidator;

    public OrderService(OrderStorage orderStorage,
                        OrderItemValidator orderItemValidator) {
        this.orderStorage = orderStorage;
        this.orderItemValidator = orderItemValidator;

    }

    //додумать логику
    public void addOrderItem(Order order, OrderItem item) {
        if (orderItemValidator.isValidItem(item)) {
            order.getOrderItems().add(item);
            logger.debug("Order item #" + item.getItemID() + " successfully added to order #" + order.getOrderId());
        } else {
            logger.debug("Item " + item.getItemID() + " is invalid. Can't add to the order.");
        }
    }

    //тоже додумать логику
    public void removeOrderItem(Order order, OrderItem item) {
        if (order.getOrderItems().contains(item)) {
            order.getOrderItems().remove(item);
            logger.debug("Item " + item.getItemID() + " was removed from order " + order.getOrderId());
        } else {
            logger.debug("Order is not contain item " + item.getItemID());
        }

    }

    public String placeOrder(Order order, User user) {
        String success = null;
        if (user.getBalance() >= getTotalCost(order)) {
            order.setOrderStatus(OrderStatus.ACCEPT);
            logger.debug("User " + user.getUserID() + " has money. Order status is " + OrderStatus.ACCEPT);
            success = orderStorage.saveOrder(order);
            if (success != null) {
                logger.debug("Order " + order.getOrderId() + " is saved to DataBase now.");
            }
        } else {
            order.setOrderStatus(OrderStatus.DECLINED);
            logger.debug("User " + user.getUserID() + "is nischebrod. Order status is " + OrderStatus.DECLINED);
        }
        return success;
    }

    public boolean sendOrderToUser(Order order, User user) {
        boolean success = false;
        final int userBalance = user.getBalance();
        if (order.getOrderStatus().equals(OrderStatus.ACCEPT)) {
            user.setBalance(userBalance - getTotalCost(order));
            logger.debug("User balance changed from " + userBalance + " to " + user.getBalance());
            order.setOrderStatus(OrderStatus.COMPLETED);
            logger.debug("Order " + order.getOrderId() + " is completed now. Status changed.");
            success = true;
        }
        return success;
    }

    public List<Order> loadAllByUserId(User user) {
        final UUID currentUserId = user.getUserID();
        List<Order> orders = new ArrayList<>();
        List<String> orderIDs = OrderIDWriterReader.readAllIDs();
        for (String orderID : orderIDs) {
            Order currentOrder = orderStorage.loadOrder(orderID);
            if (currentOrder.getUserID().equals(currentUserId)) {
                orders.add(currentOrder);
                logger.debug("Find order #" + currentOrder.getOrderId() + " and add entry to List.");
            }
        }
        if (orders.isEmpty()) {
            logger.debug("No orders loaded.");
        } else {
            logger.debug("Loaded " + orders.size() + " entries.");
        }
        return orders;
    }

    private int getTotalCost(Order order) {
        final List<OrderItem> orders = order.getOrderItems();
        int totalCost = 0;
        for (OrderItem item : orders) {
            totalCost += item.getTotalCost();
        }
        return totalCost;
    }


}
