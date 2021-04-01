package by.issoft.storage;

import by.issoft.domain.Order;

public class OrderItemStorage {

    private final String path = "resources/products.xml";
    private static int countOrders = 0;

    public String saveOrder(Order order){
        String orderID = order.getOrderId().toString();
        countOrders++;
        throw new UnsupportedOperationException("not implemented yet");
    }

    public Order loadOrder(){
        throw new UnsupportedOperationException("not implemented yet");
    }
}
