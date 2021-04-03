package by.issoft.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Order implements Serializable {
    private final List<OrderItem> orderItems = new ArrayList<>();
    private OrderStatus orderStatus;

    private final transient UUID orderId;
    private final UUID userID;
    private Date date;
    private String deliveryAddress;

    public Order(User user) {
        this.userID = user.getUserID();
        this.orderId = UUID.randomUUID();
        this.orderStatus = OrderStatus.PENDING;
    }
    public Order(User user, UUID orderId) {
        this.userID = user.getUserID();
        this.orderId = orderId;
        this.orderStatus = OrderStatus.PENDING;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public UUID getOrderId() {
        return orderId;
    }

    public UUID getUserID() {
        return userID;
    }

    public Date getDate() {
        return date;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        StringBuilder orderItemsString = new StringBuilder();
        int totalCost = 0;
        for (OrderItem item : orderItems) {
            orderItemsString.append(item).append("\n");
            totalCost += item.getTotalCost();
        }
        return "Order #" + orderId + ". Delivery address - " + deliveryAddress + ". Date - " + date.toString() +
                "\nList of items in order:\n" + orderItemsString.toString() +
                "Order status: " + orderStatus +
                "\nTotal cost: " + totalCost + "\n";

    }
}
