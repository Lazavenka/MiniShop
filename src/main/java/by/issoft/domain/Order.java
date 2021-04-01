package by.issoft.domain;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Order {
    private final List<OrderItem> orders = new ArrayList<>();
    private OrderStatus orderStatus;

    private final UUID orderId;
    private final UUID userID;
    private Date date;
    private String address;

    public Order(User user, Date date, String address){
        this.userID = user.getUserID();
        this.orderId = UUID.randomUUID();
        this.orderStatus = OrderStatus.PENDING;
        this.date = date;
        this.address = address;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public List<OrderItem> getOrders() {
        return orders;
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

    public String getAddress() {
        return address;
    }
}
