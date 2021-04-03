package by.issoft.domain.data;

import by.issoft.domain.Order;

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
    public static Order geterateOrder(){
        Order order = new Order(TestUserSamples.generateUser());
        order.setDeliveryAddress(generateAddress());
        order.setDate(new Date());
        return order;
    }
}
