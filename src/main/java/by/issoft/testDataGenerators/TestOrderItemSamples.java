package by.issoft.testDataGenerators;

import by.issoft.domain.OrderItem;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public class TestOrderItemSamples {
    private static final String[] names = {"Milk", "Pie", "Dark chocolate", "Mayo", "Spaghetti", "Coffee",
            "Apple", "Bread", "Apple juice", "Coca cola", "Tea", "Honey", "Eggs", "Sugar", "Beef"};

    public static OrderItem generateOrderItem() {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        return new OrderItem(random.nextInt(500), random.nextInt(15) + 1,
                names[random.nextInt(names.length - 1)]);
    }
    public static OrderItem validOrderItem(){
        return new OrderItem(10, 5, "Flag", UUID.fromString("28e92fe5-1c55-4531-99cd-dda875aeddc3"));
    }
}
