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
    Logger logger = LoggerFactory.getLogger(OrderService.class);

    private final Order order;
    private final OrderStorage orderStorage;
    private final OrderItemValidator orderItemValidator;

    public OrderService(Order order, OrderStorage orderStorage,
                        OrderItemValidator orderItemValidator) {
        this.order = order;
        this.orderStorage = orderStorage;
        this.orderItemValidator = orderItemValidator;

    }

    //додумать логику
    public void addOrderItem(OrderItem item) {
        if (orderItemValidator.isValidItem(item)) {
            order.getOrders().add(item);
        } else {
            logger.debug("Item " + item.getItemID() + "is invalid. Can't add to the order.");
        }
    }

    //тоже додумать логику
    public void removeOrderItem(OrderItem item) {
        if (this.order.getOrders().contains(item)) {
            order.getOrders().remove(item);
        } else {
            logger.debug("Order is not contain item " + item.getItemID());
        }

    }

    public String placeOrder(User user) {
        String orderID = "";
        if (user.getBalance() >= getTotalCost()) {
            this.order.setOrderStatus(OrderStatus.ACCEPT);
            logger.debug("User " + user.getUserID() + "is krasavchek. Order status is " + this.order.getOrderStatus());
            logger.debug("Order " + this.order.getOrderId().toString() + " is saved to DataBase now.");
            orderID = orderStorage.saveOrder(this.order);
        } else {
            this.order.setOrderStatus(OrderStatus.DECLINED);
            logger.debug("User " + user.getUserID() + "is nischebrod. Order status is " + this.order.getOrderStatus());
        }
        return orderID;
    }

    public List<Order> loadAllByUserId(User user) {
        final UUID currentUserId = user.getUserID();
        List<Order> orders = new ArrayList<>();
        for (int i = 0; i < 10; i++) {          // заглушка интерпретации перебора заказов по базе данных
            Order currentOrder = orderStorage.loadOrder();
            if (currentOrder.getUserID().equals(currentUserId)) {
                orders.add(currentOrder);
                logger.debug("Find order #" + currentOrder.getOrderId()+" ");
            }
        }
        return orders;
    }

    private int getTotalCost() {
        final List<OrderItem> orders = this.order.getOrders();
        int totalCost = 0;
        for (OrderItem item : orders) {
            totalCost += item.getTotalCost();
        }
        return totalCost;
    }


}
