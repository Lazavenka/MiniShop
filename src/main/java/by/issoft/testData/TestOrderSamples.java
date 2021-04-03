package by.issoft.testData;

import by.issoft.domain.Order;
import by.issoft.domain.User;

import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

public class TestOrderSamples {
    private static final String[] addresses = {"Lenina str. ", "Kizhevatova str. ", "Kalvariyskaya str. ",
            "Lenina str. ", "Kozlova str. ", "Krasnaya str. ", "Partyzanskiy ave. ", "Yakuba Kolasa str. ",
            "Khoruzhei str. ", "Kul'man str. "};
    private static String generateAddress(){
        ThreadLocalRandom random = ThreadLocalRandom.current();
        return addresses[random.nextInt(addresses.length - 1)] + random.nextInt(150) + " "
                + random.nextInt(200);
    }
    private static void fillOrderWithRandomItems(Order order, int count){
        for (int i = 0; i < count; i++) {
            order.getOrderItems().add(TestOrderItemSamples.generateOrderItem());
        }
    }
    public static Order geterateOrder(){
        Order order = new Order(TestUserSamples.generateUser());
        order.setDeliveryAddress(generateAddress());
        order.setDate(new Date());
        fillOrderWithRandomItems(order, 5);
        return order;
    }
    public static Order geterateOrder(User user){
        Order order = new Order(user);
        order.setDeliveryAddress(generateAddress());
        order.setDate(new Date());
        fillOrderWithRandomItems(order, 5);
        return order;
    }
}
