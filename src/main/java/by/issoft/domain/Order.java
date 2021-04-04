package by.issoft.domain;

import java.io.Serializable;
import java.util.*;

public class Order implements Serializable {
    private List<OrderItem> orderItems = new ArrayList<>();
    private OrderStatus orderStatus;

    private final UUID orderId;
    private final UUID userID;
    private String date;
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

    public String getDate() {
        return date;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(orderItems, order.orderItems) && orderStatus == order.orderStatus && Objects.equals(orderId, order.orderId) && Objects.equals(userID, order.userID) && Objects.equals(date, order.date) && Objects.equals(deliveryAddress, order.deliveryAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderItems, orderStatus, orderId, userID, date, deliveryAddress);
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
