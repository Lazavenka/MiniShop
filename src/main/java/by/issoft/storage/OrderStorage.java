package by.issoft.storage;

import by.issoft.domain.Order;

import java.util.UUID;

public class OrderStorage {
    private final String path = "resources/data.xml";
    private static int countOrders = 0;

    public Order findOrderByID(UUID id){
        throw new UnsupportedOperationException("not implemented yet");
    }

    public String saveOrder(Order order){
        String orderID = order.getOrderId().toString();
        countOrders++;
        throw new UnsupportedOperationException("not implemented yet");
    }

    public Order loadOrder(){
        throw new UnsupportedOperationException("not implemented yet");
    }

}
