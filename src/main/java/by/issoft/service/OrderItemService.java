package by.issoft.service;

import by.issoft.domain.OrderItem;
import by.issoft.storage.OrderItemStorage;

import java.util.UUID;

public class OrderItemService {
    private final OrderItemStorage orderItemStorage;

    public OrderItemService(OrderItemStorage orderItemStorage) {
        this.orderItemStorage = orderItemStorage;
    }

    public int getTotalCostOrderItem(UUID orderItemID){
        OrderItem item = orderItemStorage.loadOrderItem(orderItemID.toString());
        return item.getTotalCost();
    }

    public String createOrderItem(OrderItem orderItem){
        return orderItemStorage.saveOrderItem(orderItem);
    }
}
