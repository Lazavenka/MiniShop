package by.issoft;

import by.issoft.domain.Order;
import by.issoft.domain.User;
import by.issoft.service.*;
import by.issoft.storage.OrderItemStorage;
import by.issoft.storage.OrderStorage;
import by.issoft.testDataGenerators.TestOrderSamples;
import by.issoft.testDataGenerators.TestUserSamples;

import java.util.List;
import java.util.UUID;

public class Runner {
    public static void main(String[] args) {
        OrderStorage orderStorage = new OrderStorage();
        OrderItemStorage orderItemStorage = new OrderItemStorage();
        OrderItemValidator orderItemValidator = new OrderItemValidator();
        OrderItemService orderItemService = new OrderItemService(orderItemStorage, orderItemValidator);
        OrderService orderService = new OrderService(orderStorage,
                orderItemService);
        UserValidator userValidator = new UserValidator();
        UserService userService = new UserService(userValidator, orderStorage);

        User user1 = TestUserSamples.generateUser();
        User userConstantID = TestUserSamples.generateUserWithID(UUID.fromString("0cad2eb4-56a9-484e-b7c1-4583e6eabf24"));
        System.out.println(user1);
        System.out.println(userConstantID);
        userService.changeUserBalance(user1, 100000);
        userService.changeUserBalance(userConstantID, 100000);
        System.out.println(user1);
        System.out.println(userConstantID);

        Order order1 = TestOrderSamples.geterateOrder(userConstantID, 2);
        Order order2 = TestOrderSamples.geterateOrder(userConstantID, 2);
        Order order3 = TestOrderSamples.geterateOrder(userConstantID, 2);
        Order order4 = TestOrderSamples.geterateOrder(userConstantID, 2);

        userService.addOrder(userConstantID, order1);
        userService.addOrder(userConstantID, order2);
        userService.addOrder(userConstantID, order3);
        userService.addOrder(userConstantID, order4);

        System.out.println(order1);
        System.out.println(order2);
        System.out.println(order3);
        System.out.println(order4);

        orderService.placeOrder(order1, userConstantID);
        orderService.placeOrder(order2, userConstantID);
        orderService.placeOrder(order3, userConstantID);
        orderService.placeOrder(order4, userConstantID);

        List<Order> orders = orderService.loadAllByUserID(userConstantID);

        order1 = orders.get(0);
        order2 = orders.get(1);
        order3 = orders.get(2);
        order4 = orders.get(3);

        System.out.println(order1);
        System.out.println(order2);
        System.out.println(order3);
        System.out.println(order4);


    }
}
