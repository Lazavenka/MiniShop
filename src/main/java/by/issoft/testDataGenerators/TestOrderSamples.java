package by.issoft.testDataGenerators;

import by.issoft.domain.Order;
import by.issoft.domain.OrderItem;
import by.issoft.domain.User;
import by.issoft.storage.OrderItemStorage;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public class TestOrderSamples {
    private static final OrderItemStorage orderItemStorage = new OrderItemStorage();
    private static final String[] addresses = {"Lenina str. ", "Kizhevatova str. ", "Kalvariyskaya str. ",
            "Lenina str. ", "Kozlova str. ", "Krasnaya str. ", "Partyzanskiy ave. ", "Yakuba Kolasa str. ",
            "Khoruzhei str. ", "Kul'man str. "};
    private static String generateAddress(){
        ThreadLocalRandom random = ThreadLocalRandom.current();
        return addresses[random.nextInt(addresses.length - 1)] + random.nextInt(150) + " "
                + random.nextInt(200);
    }
    private static void fillOrderWithRandomItems(Order order, int count){
        List<UUID> items = order.getOrderItems();
        for (int i = 0; i < count; i++) {
            OrderItem orderItem = TestOrderItemSamples.generateOrderItem();
            orderItemStorage.saveOrderItem(orderItem);
            items.add(orderItem.getItemID());
        }
        order.setOrderItems(items);
    }
    public static Order geterateOrder(int orderItemsCount){
        Order order = new Order(TestUserSamples.generateUser());
        order.setDeliveryAddress(generateAddress());
        order.setDate(new Date().toString());
        fillOrderWithRandomItems(order, orderItemsCount);
        return order;
    }
    public static Order geterateOrder(User user, int orderItemsCount){
        Order order = new Order(user);
        order.setDeliveryAddress(generateAddress());
        order.setDate(new Date().toString());
        fillOrderWithRandomItems(order, orderItemsCount);
        return order;
    }

    public static Order getValidOrder(User user){
        Order order = new Order(user, UUID.fromString("05af75d6-6a1e-4e8a-acee-dde8ac7f36a9"));
        order.setDeliveryAddress("Abstract str. 42 42");
        order.setDate("9 august 2020");
        List<UUID> items = new ArrayList<>();
        OrderItem orderItem = TestOrderItemSamples.validOrderItem();
        orderItemStorage.saveOrderItem(orderItem);
        items.add(orderItem.getItemID());
        order.setOrderItems(items);
        return order;
    }
}
