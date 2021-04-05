package by.issoft.service;

import by.issoft.domain.Order;
import by.issoft.domain.OrderItem;
import by.issoft.domain.OrderStatus;
import by.issoft.domain.User;
import by.issoft.storage.OrderStorage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class OrderService {
    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);

    private final OrderStorage orderStorage;
    private final OrderItemService orderItemService;

    public OrderService(OrderStorage orderStorage,
                        OrderItemService orderItemService) {
        this.orderStorage = orderStorage;
        this.orderItemService = orderItemService;
    }

    //додумать логику
    public boolean addOrderItem(Order order, OrderItem orderItem) {
        final String orderItemID = orderItemService.createOrderItem(orderItem);
        if (orderItemID != null) {
            order.getOrderItems().add(UUID.fromString(orderItemID));
            String orderID = orderStorage.saveOrder(order);
            logger.debug("Order item #" + orderItemID + " successfully added to order #" + orderID);
            return true;
        } else {
            return false;
        }
    }

    //тоже додумать логику. пока удаляет UUID из order но serialized OrderItem остается. ну и ладно, пока норм
    public void removeOrderItem(Order order, OrderItem item) {
        if (order.getOrderItems().contains(item.getItemID())) {
            order.getOrderItems().remove(item.getItemID());
            orderStorage.saveOrder(order);
            logger.debug("Item " + item.getItemID() + " was removed from order " + order.getOrderId());
        } else {
            logger.debug("Order is not contain item " + item.getItemID());
        }
    }

    private boolean isOrderBelongToUser(Order order, User user) {
        if (order.getUserID().equals(user.getUserID())) {
            logger.debug("Order " + order.getOrderId() + " belongs to user " + user.getUserID());
            return true;
        } else {
            logger.debug("Order " + order.getOrderId() + " is not belongs to user " + user.getUserID());
            return false;
        }
    }

    public String placeOrder(Order order, User user) {
        String orderID = null;
        final int userBalance = user.getBalance();
        final int orderCost = getTotalCost(order);
        if (isOrderBelongToUser(order, user) && userBalance >= orderCost) {
            order.setOrderStatus(OrderStatus.ACCEPT);
            logger.debug("Order is correct, " + user.getUserID() + " has money. Order status is " + OrderStatus.ACCEPT);
            user.setBalance(userBalance - orderCost); //userService.changeUserBalance(user, 0 - orderCost);
            logger.debug("User balance changed from " + userBalance + " to " + user.getBalance());
            orderID = orderStorage.saveOrder(order);
            if (orderID != null) {
                logger.debug("Order " + order.getOrderId() + " is saved to DataBase now.");
            }
        } else {
            order.setOrderStatus(OrderStatus.DECLINED);
            logger.debug("User " + user.getUserID() + "is incorrect. Order status is " + OrderStatus.DECLINED);
        }
        return orderID;
    }

    public boolean sendOrderToUser(Order order, User user) {
        boolean success = false;
        if (isOrderBelongToUser(order, user) && order.getOrderStatus().equals(OrderStatus.ACCEPT)) {
            order.setOrderStatus(OrderStatus.COMPLETED);
            orderStorage.saveOrder(order);
            logger.debug("Order " + order.getOrderId() + " is completed now. Status changed.");
            success = true;
        }
        return success;
    }

    public List<Order> loadAllByUserID(User user) {
        final List<UUID> orderIDs = user.getOrderIDs();
        List<Order> orders = new ArrayList<>();
        if (orderIDs == null || orderIDs.isEmpty()) {
            return orders;
        }
        for (UUID orderID : orderIDs) {
            Order loadedOrder = orderStorage.loadOrder(orderID.toString());
            orders.add(loadedOrder);
        }
        if (orders.isEmpty()) {
            logger.debug("No orders loaded.");
        } else {
            logger.debug("Loaded " + orders.size() + " orders from files.");
        }
        return orders;
    }

    public int getTotalCost(Order order) {
        final List<UUID> orderItemIDs = order.getOrderItems();
        int totalOrderCost = 0;
        for (UUID item : orderItemIDs) {
            totalOrderCost += orderItemService.getTotalCostOrderItem(item);
        }
        return totalOrderCost;
    }


}
